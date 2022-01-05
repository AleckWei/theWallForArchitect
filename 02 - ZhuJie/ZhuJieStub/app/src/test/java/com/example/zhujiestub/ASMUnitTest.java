package com.example.zhujiestub;

import org.junit.Test;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.commons.Method;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ASM单元测试
 */
public class ASMUnitTest {

    private static final String FilePathInput = "src/test/java/com/example/zhujiestub/InjectTest.class";

    private static final String FilePathOutput = "src/test/java2/com/example/zhujiestub/InjectTest.class";

    @Test
    public void Test() throws IOException {
        File injectTestClassFile = new File(FilePathInput);
        FileInputStream fis = new FileInputStream(injectTestClassFile);

        // 将文件当中的数据，通过文件输入流，读取到内存当中
        // 构造一个byte数组
        // ClassReader内部会将FileInputStream转成byte数组
        // 所以这里就省略将文件转成byte数组的操作
        ClassReader cr = new ClassReader(fis);

        // 有读就有写
        // COMPUTE_FRAMES : 栈帧，自动计算栈帧和局部变量表的大小
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        // 执行分析，过程当中就要对class数据进行修改
        // 传一个ClassVisitor对象，访问者设计模式
        // 要去补一下这个设计模式了。。。
        // EXPAND_FRAMES android 中使用插桩，就用这个参数吧，记牢了
        cr.accept(new MyClassVisitor(Opcodes.ASM7, cw), ClassReader.EXPAND_FRAMES);

        // 执行了插桩之后的字节码数据
        byte[] bytes = cw.toByteArray();
        // 通过FileOutputStream写回到原本的文件
        // 这里为了学习对比，就放到另一个文件夹中
        FileOutputStream fos = new FileOutputStream(FilePathOutput);
        fos.write(bytes);

        // 关闭读写流
        fis.close();
        fos.close();
    }

    /**
     * 处理.class文件，将文件中的分析结果回调到调用的地方
     * 有点像OnClickListener一样
     */
    static class MyClassVisitor extends ClassVisitor {
        public MyClassVisitor(int api, ClassVisitor classVisitor) {
            super(api, classVisitor);
        }

        /**
         * 针对方法的字节码修改，需要在这个方法当中执行
         */
        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
            return new MyMethodVisitor(api, methodVisitor, access, name, descriptor);
        }
    }

    /**
     * 这里才能对method的方法体进行插桩
     */
    static class MyMethodVisitor extends AdviceAdapter {

        private int startTimeIndex;
        private boolean isTargetMethod = false;

        protected MyMethodVisitor(int api, MethodVisitor methodVisitor,
                                  int access, String name, String descriptor) {
            super(api, methodVisitor, access, name, descriptor);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            System.out.println(getName() + " : " + descriptor);
            if (descriptor.equals("Lcom/example/zhujiestub/ASMTest;")) {
                isTargetMethod = true;
            }
            return super.visitAnnotation(descriptor, visible);
        }

        /**
         * 进入方法时，会回调这个
         * 刚刚进入这个方法
         */
        @Override
        protected void onMethodEnter() {
            super.onMethodEnter();
            if (!isTargetMethod) {
                return;
            }
            // 插入 long startTime = System.currentTimeMillis();
            // 这里要涉及方法签名的知识,如果是普通的类，则需要要在路径前加一个L
            // new Method,指的是调用对应类中的方法，参数一是方法名，参数二是方法签名
            // 这个方法签名由 参数类型 + 方法返回值签名 组成
            invokeStatic(Type.getType("Ljava/lang/System;"),
                    new Method("currentTimeMillis", "()J"));
            // 这样就插入了一个 System.currentTimeMillis 的字节码

            // 先创建一个本地变量的索引(Long startTime)
            startTimeIndex = newLocal(Type.LONG_TYPE);

            // 让上一步执行的结果，用一个本地变量接收(startTime = System.currentTimeMillis)
            storeLocal(startTimeIndex);
        }

        /**
         * 要退出这个方法时，会回调这个
         */
        @Override
        protected void onMethodExit(int opcode) {
            super.onMethodExit(opcode);
            if (!isTargetMethod) {
                return;
            }
            // 插入 long endTime = System.currentTimeMillis();
            // System.out.println("execute: " + (endTime - startTime) + "ms.");

            // 同理，在方法退出时，再插入一个获取时间
            invokeStatic(Type.getType("Ljava/lang/System;"),
                    new Method("currentTimeMillis", "()J"));
            int end = newLocal(Type.LONG_TYPE);
            storeLocal(end);

            getStatic(Type.getType("Ljava/lang/System;"), "out", Type.getType("Ljava/io/PrintStream;"));
            newInstance(Type.getType("Ljava/lang/StringBuilder;"));
            dup();
            invokeConstructor(Type.getType("Ljava/lang/StringBuilder;"),
                    new Method("<init>", "()V"));
            visitLdcInsn("execute: ");
            invokeVirtual(Type.getType("Ljava/lang/StringBuilder;"),
                    new Method("append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;"));
            loadLocal(end);
            loadLocal(startTimeIndex);
            math(SUB, Type.LONG_TYPE);

            invokeVirtual(Type.getType("Ljava/lang/StringBuilder;"),
                    new Method("append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;"));

            visitLdcInsn(" ms.");
            invokeVirtual(Type.getType("LMethod java/lang/StringBuilder;"),
                    new Method("append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;"));
            invokeVirtual(Type.getType("LMethod java/lang/StringBuilder;"),
                    new Method("toString", "()Ljava/lang/String;"));
            invokeVirtual(Type.getType("Ljava/io/PrintStream;"),
                    new Method("println", "(Ljava/lang/String;)V"));
        }
    }
}
