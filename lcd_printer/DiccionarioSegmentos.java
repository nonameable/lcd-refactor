import java.util.ArrayList;
import java.util.HashMap;


public class DiccionarioSegmentos {
	
	private HashMap<Integer, ArrayList<Integer>> segmentosPorNumero;

	/**
     *
     * Constructor de la clase DiccionarioSegmento
     */
	public DiccionarioSegmentos(){
		definirSegmentos();
	}


	/**
     *
     * Metodo que define los segmentos para cada posible dígito [1,2,3,4,5,6,7,8,9,0].
     */
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
	
	/**
     *
     * Metodo que retorna los segmentos necesarios para insertar un digito en la matriz lienzo.
     * @return los segmentos correspondientes dentro de un ArrayList.
     */
	public ArrayList<Integer> darSegmentosPara(int digito) throws IllegalArgumentException{
		
		ArrayList<Integer> segmentos = segmentosPorNumero.get(digito);
		if(segmentos == null){
			throw new IllegalArgumentException("El numero ingresado no es un digito");
		}
		return segmentos;
	}

}