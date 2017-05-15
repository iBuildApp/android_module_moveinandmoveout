package com.ibuildapp.moveinandmoveout.model.updates;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Rows {
    public static class EnteredValue{
        private UserEnteredValue userEnteredValue;

        public EnteredValue(UserEnteredValue value) {
            userEnteredValue = value;
        }

        public UserEnteredValue getUserEnteredValue() {
            return userEnteredValue;
        }

        public void setUserEnteredValue(UserEnteredValue userEnteredValue) {
            this.userEnteredValue = userEnteredValue;
        }
    }
    public static class UserEnteredValue{
    }


    public static class NumberValue extends UserEnteredValue{
        private BigDecimal numberValue;

        public NumberValue(BigDecimal value) {
            this.numberValue = value;
        }

        public BigDecimal getNumberValue() {
            return numberValue;
        }

        public void setNumberValue(BigDecimal numberValue) {
            this.numberValue = numberValue;
        }
    }


    public static class StringValue extends UserEnteredValue{
        private String stringValue;

        public StringValue(String value) {
            this.stringValue = value;
        }

        public String getNumberValue() {
            return stringValue;
        }

        public void setNumberValue(String numberValue) {
            this.stringValue = numberValue;
        }
    }


    public List<EnteredValue> getValues() {
        return values;
    }

    public void setValues(List<EnteredValue> values) {
        this.values = values;
    }

    private List<EnteredValue> values;

    public static class FormulaValue extends UserEnteredValue{
        private String formulaValue;

        public FormulaValue(String value) {
            this.formulaValue = value;
        }


    }
}


