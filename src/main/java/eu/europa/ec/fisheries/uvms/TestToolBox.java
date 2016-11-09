/*
Developed by the European Commission - Directorate General for Maritime Affairs and Fisheries @ European Union, 2015-2016.

This file is part of the Integrated Fisheries Data Management (IFDM) Suite. The IFDM Suite is free software: you can redistribute it
and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of
the License, or any later version. The IFDM Suite is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
details. You should have received a copy of the GNU General Public License along with the IFDM Suite. If not, see <http://www.gnu.org/licenses/>.

*/

package eu.europa.ec.fisheries.uvms;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TestToolBox {

    private TestToolBox(){}

    /**
     *
     * Set the value of a field reflectively.
     */
    public static void setValue(Object owner, Field field, Object value) throws Exception {
        makeModifiable(field);
        field.set(owner, value);
    }

    /**
     *
     * Force the field to be modifiable and accessible.
     */
    public static void makeModifiable(Field nameField) throws Exception {
        nameField.setAccessible(true);
        int modifiers = nameField.getModifiers();
        Field modifierField = nameField.getClass().getDeclaredField("modifiers");
        modifiers = modifiers & ~Modifier.FINAL;
        modifierField.setAccessible(true);
        modifierField.setInt(nameField, modifiers);
    }
}
