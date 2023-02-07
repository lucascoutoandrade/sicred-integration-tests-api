package br.com.sicred.core.Enums;

public enum ValoresCredito {

    VALOR_DO_CREDITO_ACIMA_DO_PERMITIDO {
        public long get () {
            return 40001L;
        }
    },
    VALOR_DO_CREDITO_ABAIXO_DO_PERMITIDO {
        public long get () {
            return 999L;
        }
    },

    VALOR_DO_CREDITO_MAX_PERMITIDO {
        public long get () {
            return 40000L;
        }
    },
    VALOR_DO_CREDITO_MIN_PERMITIDO {
        public long get () {
            return 1000L;
        }
    };
    public abstract long get();
}
