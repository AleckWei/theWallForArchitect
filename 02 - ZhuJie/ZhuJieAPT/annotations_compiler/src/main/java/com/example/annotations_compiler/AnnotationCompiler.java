package com.example.annotations_compiler;

import com.example.annotation.BindView;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * 注解处理器，用于生成代码
 */
@AutoService(Processor.class)
public class AnnotationCompiler extends AbstractProcessor {

    // 生成java文件，必须在生成代码之前被初始化才行
    Filer filer;

    // 获取注解处理器相关的日志信息
    Messager messager;

    // 执行注解处理器的各种初始化工作
    // ProcessingEnvironment是一个工具类的接口
    // 可以用它来获取各种工具
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "process init");
    }

    // getSupportedAnnotationTypes 以及 getSupportedSourceVersion
    // 可以在类上面用注解的方式进行声明

    // 指定本处理器支持处理的注解类型
    // 当前框架仅处理BindView这个注解
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationSet = new HashSet<>();
        annotationSet.add(BindView.class.getCanonicalName());
        return annotationSet;
    }

    // 声明支持的java版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        // processingEnv 属性是写在 AbstractProcessor 当中的
        // 方便地供子类使用
        return processingEnv.getSourceVersion();
    }

    // 专门用于搜索代码中注解的方法
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 生成代码的地方
        // 1. 搜索用到了BindView的注解
        Set<? extends Element> elements =
                roundEnvironment.getElementsAnnotatedWith(BindView.class);

        // 执行方法的节点
        // ExecutableElement
        // 成员变量的节点
        // VariableElement
        // 类的节点
        // TypeElement
        // 包的节点
        // PackageElement

        // 2. 把每个activity和它里面的内容放在一起，activity与activity分开
        HashMap<String, List<VariableElement>> map = new HashMap<>();
        for (Element element : elements) {
            VariableElement vm = (VariableElement) element;
            // 获取成员变量所在的类的类名
            TypeElement typeElement = (TypeElement) vm.getEnclosingElement();
            // 获取类节点的不带包名的名称
            String activityName = typeElement.getSimpleName().toString();
            List<VariableElement> elementList = map.get(activityName);
            if (elementList == null) {
                elementList = new ArrayList<>();
                map.put(activityName, elementList);
            }
            elementList.add(vm);
        }

        // 3. 生成代码
        if (map.size() > 0) {
            Writer writer = null;
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String activityName = iterator.next();
                List<VariableElement> variableElements = map.get(activityName);
                // 获取到包名
                String packageName = getPackageName(variableElements.get(0));
                // 创建一个类名 （如ButterKnife当中的 clsName + "_ViewBinding"）
                String newActivityName = activityName + "_ViewBinding";
                try {
                    JavaFileObject sourceFile = filer.createSourceFile(packageName + "." + newActivityName);
                    writer = sourceFile.openWriter();
                    StringBuffer sb = new StringBuffer();

                    sb.append("package ").append(packageName).append(";\n");
                    sb.append("import android.view.View;\n");
                    sb.append("public class ")
                            .append(newActivityName)
                            .append(" implements IButterKnife<")
                            .append(packageName).append(".").append(activityName)
                            .append("> {\n");
                    sb.append("public void bind (")
                            .append(packageName).append(".").append(activityName).append(" target) { \n");
                    for (VariableElement variableElement : variableElements) {
                        int resId = variableElement.getAnnotation(BindView.class).value();
                        String variableName = variableElement.getSimpleName().toString();
                        sb.append("target.").append(variableName)
                                .append("=target.findViewById(").append(resId).append(");\n");
                    }
                    sb.append("} \n } \n");
                    writer.write(sb.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            messager.printMessage(Diagnostic.Kind.WARNING,
                    "map's is 0");
        }
        return false;
    }

    /**
     * 获取父节点的完整包名
     *
     * @param element 子节点
     * @return (String) 父节点的全包名
     */
    private String getPackageName(VariableElement element) {
        Element parentElement = element.getEnclosingElement();
        PackageElement packageElement = processingEnv.getElementUtils().getPackageOf(parentElement);
        return packageElement.getQualifiedName().toString();
    }

}