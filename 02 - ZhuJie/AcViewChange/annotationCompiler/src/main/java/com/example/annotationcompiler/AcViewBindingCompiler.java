package com.example.annotationcompiler;

import com.example.annotation.AcView.annotation.AcViewModelAnnotation;
import com.example.annotation.AcView.annotation.AcViewPresenterAnnotation;
import com.google.auto.service.AutoService;

import java.io.Writer;
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
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

/**
 * 处理acView相关的注解处理器
 */
@AutoService(Processor.class)
public class AcViewBindingCompiler extends AbstractProcessor {

    private Filer mFiler;
    private Messager messager;

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
        // 1.先处理 AcViewPresenterAnnotation
        Set<? extends Element> presenterSets
                = roundEnvironment.getElementsAnnotatedWith(AcViewModelAnnotation.class);

        for (Element presenterSet : presenterSets) {
            Writer writer = null;
            // 获取到这个presenter的类
            TypeElement typeEle = (TypeElement) presenterSet;
            // 获取该presenter的全包名
            String presenterClassName = typeEle.getQualifiedName().toString();
            // 获取注解中对应的mid
            String mid = typeEle.getAnnotation(AcViewPresenterAnnotation.class).value();

            // 生成对应代码
            // 1) 获取presenter所在包元素的完整路径
            PackageElement presentPackageElement = processingEnv.getElementUtils().getPackageOf(typeEle);
            String presenterPackageName = presentPackageElement.getQualifiedName().toString();

            // 2) 获取acview包的的完整逻辑
            PackageElement parentPackageElement = (PackageElement) presentPackageElement.getEnclosingElement();
            String acViewPackageName = parentPackageElement.getQualifiedName().toString();

        }

        return false;
    }
}
