package com.edebtor.oclimb.edebtor;

    public class crdit_histry_item {

        private int id;
        private String date;
        private String debetor_name;
        private String credit;


        public crdit_histry_item(int id, String date, String debetor_name, String credit) {

            this.id = id;
            this.date = date;
            this.debetor_name = debetor_name;
            this.credit = credit;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDebetor_name() {
            return debetor_name;
        }

        public void setDebetor_name(String debetor_name) {
            this.debetor_name = debetor_name;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }
}
