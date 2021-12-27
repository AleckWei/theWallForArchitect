package com.example.annotationcompiler;

import com.example.annotation.AcView.annotation.AcViewModelAnnotation;
import com.example.annotation.AcView.annotation.AcViewPresenterAnnotation;
import com.google.auto.service.AutoService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

/**
 * 处理acView相关的注解处理器
 */
@AutoService(Processor.class)
public class AcViewBindingCompiler extends AbstractProcessor {

    private Filer mFiler;
    private Messager messager;
    private static final String FileName = "AcView_Binder";
    private static final String PackageName = "com.example.annotation.AcView.acviwbinder";

    private static final String USED_MODULE = "app";

    private static final String MOVE_TO_MODULE = "annotation";

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        mFiler = processingEnv.getFiler();
    }

    /**
     * 本注解处理器要处理的是 AcViewPresenter 和 AcViewModel
     * 对应的注解
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(AcViewPresenterAnnotation.class.getCanonicalName());
        set.add(AcViewModelAnnotation.class.getCanonicalName());
        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 1. 生成对应包下的.java文件
        String path = PackageName + "." + FileName;
        messager.printMessage(Diagnostic.Kind.NOTE, "全类名：" + path);

        OutputStreamWriter writer = null;

        try {
            //找到项目目录的相对路径
            FileObject resource = mFiler.createResource(StandardLocation.SOURCE_OUTPUT, "", "tmpFile");
            String resourcePath = resource.toUri().getPath();
            resource.delete();
            messager.printMessage(Diagnostic.Kind.NOTE, "生成一个文件，准备获取相对路径:" + resourcePath);

            //截取我们需要的路径，然后切换模块 从 app 切换到>>> MODULE_NAME
            String relative = resourcePath.substring(0, resourcePath.indexOf(USED_MODULE) + 4).replaceAll(USED_MODULE, MOVE_TO_MODULE);

            //找到对应模块下的路径
            String javaPath = "src/main/java/";
            //最终生成的名称路径
            String appPath = relative + javaPath + PackageName.replace(".", "/");
            messager.printMessage(Diagnostic.Kind.NOTE, "appPath:" + appPath);

            File file = new File(appPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            File binderJavaFile = new File(file, FileName + ".java");
            if (binderJavaFile.exists()) {
                binderJavaFile.delete();
            }
            binderJavaFile.createNewFile();

            FileOutputStream fos = new FileOutputStream(binderJavaFile);

            writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

            // 直接开始写代码
            StringBuilder sb = new StringBuilder();
            sb.append("package ").append(PackageName + ";\n\n");
            sb.append("import java.util.HashMap;\n");
            sb.append("import java.util.Map;\n\n");

            sb.append("/**\n * @author WWJ\n * @detail: 由代码生成的binder类，不需手写维护。这里供debug使用\n */\n");

            sb.append("public class " + FileName + " {\n");
            sb.append("    public static final int PRESENTER_TYPE = 0;\n");
            sb.append("    public static final int MODEL_TYPE = 1; \n");
            sb.append("    private static final Map<String, String> mAcViewPresenterMap = new HashMap<>();\n");
            sb.append("    private static final Map<String, String> mAcViewModelMap = new HashMap<>(); \n\n");

            sb.append("    public static Map<String, String> getMap(int type) {\n");
            sb.append("        switch (type) {\n");
            sb.append("            case PRESENTER_TYPE:\n");
            sb.append("                return getPresenterMap();\n");
            sb.append("            case MODEL_TYPE:\n");
            sb.append("                return getModelMap();\n");
            sb.append("            default:\n");
            sb.append("                return null;\n");
            sb.append("        }\n    }\n");

            sb.append("    private static Map<String, String> getPresenterMap() {\n");
            sb.append("        if (mAcViewPresenterMap.size() == 0) {\n");

            messager.printMessage(Diagnostic.Kind.NOTE, " ==== presenter ==== ");

            // 1.先处理 AcViewPresenterAnnotation
            Set<? extends Element> presenterSets
                    = roundEnvironment.getElementsAnnotatedWith(AcViewPresenterAnnotation.class);
            if (presenterSets.size() > 0) {
                for (Element presenterSet : presenterSets) {
                    // 获取到这个presenter的类
                    TypeElement typeEle = (TypeElement) presenterSet;
                    // 获取该presenter的全包名
                    String presenterClassName = typeEle.getQualifiedName().toString();
                    // 获取注解中对应的mid
                    String mid = typeEle.getAnnotation(AcViewPresenterAnnotation.class).value();
                    messager.printMessage(Diagnostic.Kind.NOTE, mid + " : " + presenterClassName);
                    sb.append("            mAcViewPresenterMap.put(\"")
                            .append(mid)
                            .append("\", \"")
                            .append(presenterClassName)
                            .append("\");\n");
                }
            }
            sb.append("        }\n");
            sb.append("        return mAcViewPresenterMap;\n    }\n\n");

            messager.printMessage(Diagnostic.Kind.NOTE, " ==== model ==== ");

            sb.append("    private static Map<String, String> getModelMap() {\n");
            sb.append("        if (mAcViewModelMap.size() == 0) {\n");
            // 2.再处理 AcViewModelAnnotation
            Set<? extends Element> modelSets
                    = roundEnvironment.getElementsAnnotatedWith(AcViewModelAnnotation.class);
            if (modelSets.size() > 0) {
                for (Element modelSet : modelSets) {
                    // 获取到这个model的类
                    TypeElement typeEle = (TypeElement) modelSet;
                    // 获取该model的全包名
                    String modelClassName = typeEle.getQualifiedName().toString();
                    // 获取注解中对应的mid
                    String mid = typeEle.getAnnotation(AcViewModelAnnotation.class).value();
                    messager.printMessage(Diagnostic.Kind.NOTE, mid + " : " + modelClassName);
                    sb.append("            mAcViewModelMap.put(\"")
                            .append(mid)
                            .append("\", \"")
                            .append(modelClassName)
                            .append("\");\n");
                }
            }
            sb.append("        }\n");
            sb.append("        return mAcViewModelMap;\n    }\n}");

            writer.write(sb.toString());
        } catch (Exception e) {
            messager.printMessage(Diagnostic.Kind.WARNING, e.toString());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
