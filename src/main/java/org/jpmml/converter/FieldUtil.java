package org.jpmml.converter;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dmg.pmml.Field;
import org.dmg.pmml.HasDiscreteDomain;
import org.dmg.pmml.Value;
import org.dmg.pmml.Value.Property;

public class FieldUtil {
    private FieldUtil() {
    }

    public static <F extends Field<F> & HasDiscreteDomain<F>> List<?> getValues(F field) {
        return getValues(field, (Property)null);
    }

    public static <F extends Field<F> & HasDiscreteDomain<F>> List<?> getValues(F field, Property property) {
        List<Object> result = new ArrayList();
        if (property == null) {
            property = Property.VALID;
        }

        List<Value> pmmlValues = ((HasDiscreteDomain)field).getValues();
        Iterator var4 = pmmlValues.iterator();

        while(var4.hasNext()) {
            Value pmmlValue = (Value)var4.next();
            if (property == pmmlValue.getProperty()) {
                result.add(pmmlValue.requireValue());
            }
        }

        return result;
    }

    public static <F extends Field<F> & HasDiscreteDomain<F>> void addValues(F field, List<?> values) {
        addValues(field, (Property)null, values);
    }

    public static <F extends Field<F> & HasDiscreteDomain<F>> void addValues(F field, Property property, List<?> values) {
        if (property == Property.VALID) {
            property = null;
        }

        List<Value> pmmlValues = ((HasDiscreteDomain)field).getValues();
        Iterator var4 = values.iterator();

        while(var4.hasNext()) {
            Object value = var4.next();
            Value pmmlValue = (new Value(value)).setProperty(property);
            pmmlValues.add(pmmlValue);
        }

    }

    public static <F extends Field<F> & HasDiscreteDomain<F>> void clearValues(F field, Property property) {
        if (property == null) {
            property = Property.VALID;
        }

        List<Value> pmmlValues = ((HasDiscreteDomain)field).getValues();
        Iterator it = pmmlValues.iterator();

        while(it.hasNext()) {
            Value pmmlValue = (Value)it.next();
            if (pmmlValue.getProperty() == property) {
                it.remove();
            }
        }

    }
}
