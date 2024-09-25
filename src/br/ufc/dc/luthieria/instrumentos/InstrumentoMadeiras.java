package br.ufc.dc.luthieria.instrumentos;



public class InstrumentoMadeiras extends InstrumentoAbstrato {

    private String materialCorpo;
    private String tipoPalheta;

    public InstrumentoMadeiras(String nome, String marca, String codigo, EstadoInstrumento estado, String materialCorpo, String tipoPalheta) {
        super(nome, marca, codigo, estado);
        this.materialCorpo = materialCorpo;
        this.tipoPalheta = tipoPalheta;
    }

    public String getMaterialCorpo() {
        return materialCorpo;
    }

    public void setMaterialCorpo(String materialCorpo) {
        this.materialCorpo = materialCorpo;
    }

    public String getTipoPalheta() {
        return tipoPalheta;
    }

    public void setTipoPalheta(String tipoPalheta) {
        this.tipoPalheta = tipoPalheta;
    }

    @Override
    public String toString() {
        return super.toString() + ", material do corpo='" + materialCorpo + "', tipo de palheta='" + tipoPalheta + '\'';
    }
}
