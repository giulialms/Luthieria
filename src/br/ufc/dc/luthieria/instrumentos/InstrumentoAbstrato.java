package br.ufc.dc.luthieria.instrumentos;

public class InstrumentoAbstrato {

 	private String nome;
 	private String codigo;
    private String marca;
    private EstadoInstrumento estado;

	    public InstrumentoAbstrato(String nome, String marca, String codigo, EstadoInstrumento estado) {
	    //public Instrumento(String nome, String tipo) {
	    	this.nome = nome;
	        this.codigo = codigo;
	        this.marca = marca;
	        this.estado = estado;
	    }
	    
	    
	    public String getCodigo() {
			return codigo;
		}



		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}



		public String getMarca() {
			return marca;
		}



		public void setMarca(String marca) {
			this.marca = marca;
		}


		public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public EstadoInstrumento getEstado() {
	        return estado;
	    }

	    public void setEstado(EstadoInstrumento estado) {
	        this.estado = estado;
	    }

	    @Override
	    public String toString() {
	        return "Instrumento{" +
	                "nome='" + nome + '\'' +
	                ", estado=" + estado +
	                '}';
	    }
	}
	    

