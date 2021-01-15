package Q48;

public class Self {
    
}

class Solution {
    public void rotate(int[][] matrix) {
        int len = matrix.length - 1;   
        int half = matrix.length / 2;
        if(len % 2 == 0) half++;
        for(int i = 0; i < half; i++){
            for(int j = 0; j < half; j++){
                //  奇数矩阵， 中间线 不能重复旋转
                if(len % 2 == 0 && i == half - 1)   // 奇数矩阵
                {
                    continue;
                }
                //  由第二象限，去映射 其他象限的坐标。len - i，len - j。i,j表示距离(0，0)点的距离。
                // 这样，每个象限都可以表示为 
                /*  象限的坐标
                    2. [i][j]          
                    1. [j][len - i]    由映射关系， 二象限点 距离其象限边界的距离和 1象限 距离其边界的距离相等。
                    3. [len - j][i]
                    4. [len - i][len - j] 
                    
                */
                int a = matrix[i][j];
                matrix[i][j] = matrix[len - j][i];
                matrix[len - j][i] = matrix[len - i][len - j];
                matrix[len - i][len - j] = matrix[j][len - i];
                matrix[j][len - i] = a;
            }
        }
    }
}

/*class

3:::2
15  1  9  5  
2  4  8  10  
13  3  6  7  
16  14  12  11  


15  13  9  5  
2  4  8  1  
12  3  6  7  
16  14  10  11  


15  13  2  5  
14  4  8  1  
12  3  6  9  
16  7  10  11  


15  13  14  5  
7  4  8  1  
12  3  6  2  
16  9  10  11  


15  13  14  5  
7  3  4  1  
12  6  8  2  
16  9  10  11  


15  13  14  5  
7  6  3  1  
12  8  4  2  
16  9  10  11  


15  12  14  5  
7  6  3  13  
10  8  4  2  
16  9  1  11  


15  12  14  5  
7  8  6  13  
10  4  3  2  
16  9  1  11  



2:::1
7  2  1  
4  5  6  
9  8  3  


7  4  1  
8  5  2  
9  6  3  


7  8  1  
6  5  4  
9  2  3  


7  8  1  
6  5  4  
9  2  3  
*/