package dam.spring.handler;

import dam.annotation.UnderDamControl;
import dam.cache.MethodResultCache;
import dam.spring.defenation.Gate;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Map;
import java.util.Set;

public class AnnotationScanner extends ClassPathBeanDefinitionScanner {

    public AnnotationScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected void registerDefaultFilters() {
        addIncludeFilter(new AnnotationTypeFilter(UnderDamControl.class));
        super.registerDefaultFilters();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitions) {
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
            definition.getPropertyValues().add("clazz", definition.getBeanClassName());
            Map<String, Object> attributes = ((ScannedGenericBeanDefinition) definition).getMetadata().getAnnotationAttributes(UnderDamControl.class.getName(), false);
            if (attributes.containsKey("id")) {
                definition.getPropertyValues().add("id", attributes.get("id"));
            }
            long size = 1000;
            long duration = 10;
            if (attributes.containsKey("size")) {
                size = (Long) attributes.get("size");
            }
            if (attributes.containsKey("duration")) {
                duration = (Long) attributes.get("duration");
            }

            definition.getPropertyValues().add("cache", new MethodResultCache(size, duration));
            definition.setBeanClass(Gate.class);
        }
        return beanDefinitions;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return super.isCandidateComponent(beanDefinition) && beanDefinition.getMetadata().hasAnnotation(UnderDamControl.class.getName());
    }
}
