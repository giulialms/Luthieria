package br.ufc.dc.luthieria.instrumentos;



public class InstrumentoMetais extends InstrumentoAbstrato {

    private String materialCorpo;
    private String tipoBocal;

    public InstrumentoMetais(String nome, String marca, String codigo, EstadoInstrumento estado, String materialCorpo, String tipoBocal) {
        super(nome, marca, codigo, estado);
        this.materialCorpo = materialCorpo;
        this.tipoBocal = tipoBocal;
    }

    public String getMaterialCorpo() {
        return materialCorpo;
    }

    public void setMaterialCorpo(String materialCorpo) {
        this.materialCorpo = materialCorpo;
    }

    public String getTipoBocal() {
        return tipoBocal;
    }

    public void setTipoBocal(String tipoBocal) {
        this.tipoBocal = tipoBocal;
    }

    @Override
    public String toString() {
        return super.toString() + ", material do corpo='" + materialCorpo + "', tipo de bocal='" + tipoBocal + '\'';
    }
}
