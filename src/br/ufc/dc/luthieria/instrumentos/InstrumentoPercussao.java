package br.ufc.dc.luthieria.instrumentos;



public class InstrumentoPercussao extends InstrumentoAbstrato {

    private String tipoPele;
    private String tipoBaqueta;

    public InstrumentoPercussao(String nome, String marca, String codigo, EstadoInstrumento estado, String tipoPele, String tipoBaqueta) {
        super(nome, marca, codigo, estado);
        this.tipoPele = tipoPele;
        this.tipoBaqueta = tipoBaqueta;
    }

    public String getTipoPele() {
        return tipoPele;
    }

    public void setTipoPele(String tipoPele) {
        this.tipoPele = tipoPele;
    }

    public String getTipoBaqueta() {
        return tipoBaqueta;
    }

    public void setTipoBaqueta(String tipoBaqueta) {
        this.tipoBaqueta = tipoBaqueta;
    }

    @Override
    public String toString() {
        return super.toString() + ", tipo de pele='" + tipoPele + "', tipo de baqueta='" + tipoBaqueta + '\'';
    }
}
