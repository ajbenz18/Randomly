package edu.msu.bensmana.randomly.Snippets.Models;

import org.simpleframework.xml.Attribute;

public class Advice {

    @Attribute
    private Slip slip;

    public Slip getSlip() {
        return slip;
    }

    public void setSlip(Slip slip) {
        this.slip = slip;
    }

    public Advice(){}

    public Advice(Slip slip){
        this.slip = slip;
    }

    public class Slip{
        @Attribute
        private String slip_id;

        @Attribute
        private String advice;

        public String getSlip_id() {
            return slip_id;
        }

        public void setSlip_id(String slip_id) {
            this.slip_id = slip_id;
        }

        public String getAdvice() {
            return advice;
        }

        public void setAdvice(String advice) {
            this.advice = advice;
        }

        public Slip(){

        }

        public Slip(String slip_id, String advice){
            this.slip_id = slip_id;
            this.advice = advice;
        }
    }
}


