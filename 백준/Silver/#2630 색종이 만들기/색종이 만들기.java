import java.util.*;
import java.io.*;

class Main{
    static int white = 0;
    static int blue = 0;
    static int[][] board;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        partition(0,0,N);
    
        bw.write(white + "\n" + blue + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static void partition(int row, int col, int size){
        
        if(colorCheck(row, col, size)){
            if(board[row][col] == 0)
                white++;
            else
                blue++;
            return;    
        }

        int newSize = size / 2;	// 절반 사이즈
		// 재귀 호출
		partition(row, col, newSize);						// 2사분면
		partition(row, col + newSize, newSize);				// 1사분면
		partition(row + newSize, col, newSize);				// 3사분면
		partition(row + newSize, col + newSize, newSize);	// 4사분면
    }

    // 현재 파티션의 컬러가 같은지 체크.
	public static boolean colorCheck(int row, int col, int size) {
	
		int color = board[row][col];	// 첫 번째 원소를 기준으로 검사
		
		for(int i = row; i < row + size; i++) {
			for(int j = col; j < col + size; j++) {
				// 색상이 같이 않다면 false를 리턴 
				if(board[i][j] != color) {
					return false;
				}
			}
		}
		// 검사가 모두 통과했다는 의미이므로 true 리턴
		return true;
	}
}