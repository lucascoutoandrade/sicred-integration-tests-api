package br.com.sicred.core.Enums;

public enum Clientes {

    CLIENTE_COM_RESTRICAO_NO_CPF {
        public String get(){
           return  "60094146012";
        }
    },
    CLIENTE_SEM_RESTRICAO_NO_CPF {
        public String get() {
            return "75396274093";
        }
    };

    public abstract String get();

}
