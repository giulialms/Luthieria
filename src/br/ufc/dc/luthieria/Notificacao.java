package br.ufc.dc.luthieria;
import br.ufc.dc.luthieria.ordens.OrdemServico;

public class Notificacao {
	public String imprimirNotificacao(OrdemServico ordem) {
		/*
		 System.out.print("O instrumento " + ordem.getInstrumento().getNome());
	     System.out.print(" da marca " + ordem.getInstrumento().getMarca());
	     System.out.print(", em nome do cliente " + ordem.getInstrumento().getProprietario().getNome());
	     System.out.print(" e de codigo " + ordem.getId());
	     System.out.println(" " + ordem.getInstrumento().getEstado().getDescricao());
	     */
	     if(ordem.getMaterial().equals("")) {
	    	 return("O instrumento " + ordem.getInstrumento().getNome() +
	    		     " da marca " + ordem.getInstrumento().getMarca() +
	    		     ", em nome do cliente " + ordem.getCliente().getNome() +
	    		     " e de codigo " + ordem.getId() + " " + ordem.getInstrumento().getEstado().getDescricao() +
	    			 " e não necessitou de material adicional");
	     }
	     else {
			 return("O instrumento " + ordem.getInstrumento().getNome() +
		     " da marca " + ordem.getInstrumento().getMarca() +
		     ", em nome do cliente " + ordem.getCliente().getNome() +
		     " e de codigo " + ordem.getId() + " " + ordem.getInstrumento().getEstado().getDescricao() +
	    	 ", está sendo utilizado " + ordem.getMaterial());
	     }
		 //Exemplo 2 de notificação: O Instrumento [NOME INSTRUMENTO], em nome do cliente [NOME CLIENTE],
	     //está em [TIPO SERVICO]  e tem previsão de ser entregue dia [PRAZO], segundo a ordem de serviço
	     //número [CODIGO SERVICO], está sendo [NECESSITOU MATERIAL] [MATERIAL UTILIZADO]. 

	}

}
