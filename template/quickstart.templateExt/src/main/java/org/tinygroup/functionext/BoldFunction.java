package org.tinygroup.functionext;

import org.tinygroup.template.Template;
import org.tinygroup.template.TemplateContext;
import org.tinygroup.template.TemplateException;
import org.tinygroup.template.function.AbstractTemplateFunction;

/**
 * Created by BYSocket on 2015/11/18.
 */
public class BoldFunction extends AbstractTemplateFunction {

    public BoldFunction() {
        super("bold");
    }

    @Override
    public String getBindingTypes() {
        return "java.lang.String";
    }

    @Override
    public Object execute(Template template,
                          TemplateContext context,
                          Object... parameters) throws TemplateException {
        return "<b>" + parameters[0].toString() + "</b>";
    }
}
