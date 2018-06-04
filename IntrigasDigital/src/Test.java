import java.util.function.Consumer; //essa classe testa a funcionalidade Consumer do java 8
//ela serve para colocar uma funcao void como parametro ou atributo

//desisti disso na real, sei lah como usa
public class Test {
	
	public static Consumer c;
	
	
	public static void main(String args[]) {
	//	Test.c = Test::printCool;
		//Consumer consumer = Test.printCool;
	
	}
	
	
	static void printCool(String add) {
		System.out.print("Cool" + add);
	}
}
