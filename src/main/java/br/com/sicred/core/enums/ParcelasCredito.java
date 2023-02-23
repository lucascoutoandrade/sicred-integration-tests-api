package br.com.sicred.core.enums;

public enum ParcelasCredito {
    NUMERO_PARCELAS_ABAIXO_DO_PERMITIDO {
        public int get() {
            return 1;
        }
    },
    NUMERO_PARCELAS_MIN_PERMITIDO {
        public int get() {
            return 2;
        }
    },
    NUMERO_PARCELAS_MAX_PERMITIDO {
        public int get() {
            return 48;
        }
    },
    NUMERO_PARCELAS_ACIMA_DO_PERMITIDO {
        public int get() {
            return 49;
        }
    };

    public abstract int get();


}
