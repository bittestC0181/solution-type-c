package problem04;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	
	
	public static void main(String[] args) {
		
		System.out.println("게임시작!!!");
		System.out.println("게임 ? ===>> 게임 시작(1), 프로그램 종료(1이 아닌 숫자)");
		System.out.println("게임 진행 중 -1를 입력하면 진행중인 게임 포기");
		
		QnA[] qna = new QnA[18];
		
		for ( int i=0; i<9; i++ ) {
			int multiplier1 = MakeMultipiler(i);
			String question = (i+1)+"x"+multiplier1;
			int answer = (i+1)*multiplier1;
			qna[i*2] = new QnA(question, answer);
			
			int tmp;
			while ( multiplier1 != (tmp = MakeMultipiler(i))) ;
			int multiplier2 = tmp;
			question = (i+1)+"x"+multiplier2;
			answer = (i+1)*multiplier2;
			qna[i*2+1] = new QnA(question, answer);
		}
		
		RandomizeArray(qna);
		
		ArrayList<Result> result = new ArrayList<Result>();
		Scanner scanner = new Scanner(System.in);
		while ( true ) {
			int input = scanner.nextInt();
			System.out.println("게임?");
			if ( input == 1 ) {
				long startTime = System.nanoTime();
				Result resultOfGame = Game(qna);
				long endTime = System.nanoTime();
				long duration = (endTime - startTime);
				resultOfGame.setDuration(duration);
				result.add(resultOfGame);
			}else
				break;
		}
		
		result.sort(Comparator.comparing(Result::getSuccess).thenComparing(Result::getDuration));
		
		for ( int i=0; i< result.size(); i++ ) {
			System.out.println(i+1+" :"+result.get(i).getSuccess()+" / :"+result.get(i).getFail());
		}
		
	}
	
	public static Result Game(QnA[] qna) {
		Scanner scanner = new Scanner(System.in);
		int success = 0;
		int fail = 0;
		for ( int i=0; i<18; i++ ) {
			System.out.println(i+". "+qna[i].getQuestion()+" ?");
			while ( true ) {
				int input = scanner.nextInt();
				if ( input == -1 ) {
					return new Result(success, fail);
				}
				if ( input == qna[i].getAnswer() ) {
					success++;
					break;
				}
				System.out.println("땡!!");
				fail++;
			}
		}
		return new Result(success, fail);
	}
	
	public static void RandomizeArray(QnA[] array){
		Random rgen = new Random();  // Random number generator			
 
		for (int i=0; i<array.length; i++) {
		    int randomPosition = rgen.nextInt(array.length);
		    QnA temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
	}
	
	public static int MakeMultipiler(int i) {
		int multiplier = new Random().nextInt(9) + 1; 
		return multiplier;
	}

}

class QnA {
	String question;
	int answer;
	
	public QnA(String question, int answer) {
		this.question = question;
		this.answer = answer;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public int getAnswer() {
		return this.answer;
	}
}

class Result {
	int success;
	int fail;
	long duration;
	
	public Result(int success, int fail) {
		this.success = success;
		this.fail = fail;
	}
	
	public int getFail() {
		return this.fail;
	}

	public int getSuccess() {
		return this.success;
	}
	
	public long getDuration() {
		return this.duration;
	}
	
	public void setDuration(long duration) {
		this.duration = duration;
	}
}