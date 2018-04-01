import java.util.ArrayList;
import java.util.HashMap;


public class DiccionarioSegmentos {
	
	private HashMap<Integer, ArrayList<Integer>> segmentosPorNumero;
	
	public DiccionarioSegmentos(){
		definirSegmentos();
	}

	private void  definirSegmentos() {
		segmentosPorNumero = new HashMap<>();

		ArrayList<Integer> segmentos = new ArrayList<>();
		segmentos.add(1);
		segmentos.add(2);
		segmentos.add(3);
		segmentos.add(4);
		segmentos.add(5);
		segmentos.add(7);
		segmentosPorNumero.put(0, segmentos);

		segmentos = new ArrayList<>();
		segmentos.add(3);
		segmentos.add(4);
		segmentosPorNumero.put(1, segmentos);
		
		segmentos = new ArrayList<>();
		segmentos.add(5);
		segmentos.add(3);
		segmentos.add(6);
		segmentos.add(2);
		segmentos.add(7);
		segmentosPorNumero.put(2, segmentos);

		segmentos = new ArrayList<>();
		segmentos.add(5);
		segmentos.add(3);
		segmentos.add(6);
		segmentos.add(4);
		segmentos.add(7);
		segmentosPorNumero.put(3, segmentos);

		segmentos = new ArrayList<>();
		segmentos.add(1);
		segmentos.add(6);
		segmentos.add(3);
		segmentos.add(4);
		segmentosPorNumero.put(4, segmentos);

		segmentos = new ArrayList<>();
		segmentos.add(5);
		segmentos.add(1);
		segmentos.add(6);
		segmentos.add(4);
		segmentos.add(7);
		segmentosPorNumero.put(5, segmentos);

		segmentos = new ArrayList<>();
		segmentos.add(5);
		segmentos.add(1);
		segmentos.add(6);
		segmentos.add(2);
		segmentos.add(7);
		segmentos.add(4);
		segmentosPorNumero.put(6, segmentos);

		segmentos = new ArrayList<>();
		segmentos.add(5);
		segmentos.add(3);
		segmentos.add(4);
		segmentosPorNumero.put(7, segmentos);

		segmentos = new ArrayList<>();
		segmentos.add(1);
		segmentos.add(2);
		segmentos.add(3);
		segmentos.add(4);
		segmentos.add(5);
		segmentos.add(6);
		segmentos.add(7);
		segmentosPorNumero.put(8, segmentos);

		segmentos = new ArrayList<>();
		segmentos.add(1);
		segmentos.add(3);
		segmentos.add(4);
		segmentos.add(5);
		segmentos.add(6);
		segmentos.add(7);
		segmentosPorNumero.put(9, segmentos);
	}
	
	public ArrayList<Integer> darSegmentosPara(int digito) {
		return segmentosPorNumero.get(digito);
	}

}