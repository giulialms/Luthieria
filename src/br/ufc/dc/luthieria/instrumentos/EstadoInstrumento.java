package br.ufc.dc.luthieria.instrumentos;

public enum EstadoInstrumento {
    RECEBIDO("foi recebido na loja"),
    EM_CONSERTO("está em conserto"),
    EM_MANUTENCAO("está em manutenção"),
    PRONTO("está pronto para entrega"),
    EM_FABRICACAO("está em fabricação");

    private String descricao;

   
    EstadoInstrumento(String descricao) {
        this.descricao = descricao;
    }

    
    public String getDescricao() {
        return descricao;
    }
}
