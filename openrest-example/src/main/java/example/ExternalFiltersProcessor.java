package example;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

@SupportedAnnotationTypes("pl.openrest.filters.predicate.annotation.PredicateRepository")
public class ExternalFiltersProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement type : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(type)) {
                for (Element el : element.getEnclosedElements()) {
                    if (el instanceof ExecutableElement) {
                        ExecutableElement method = (ExecutableElement) el;
                        for(VariableElement p: method.getParameters())
                            System.out.println(p.getSimpleName());
                    }
                }

            }
        }
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
