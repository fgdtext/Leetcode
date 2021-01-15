
#include<bits/stdc++.h>
using namespace std;
/*
test_Q2 ：双指针 替换单词 例如 输入替换目标 you 若一个单词由 you，ouy, uoy (各字符数出现相同)
                      将该单词替换掉



    双指针法。 
   一个字母可能多次出现。所以要 用次数来表征，是否出现够了。
    time[26] 用于记录 该 单词 各个 字母出现的次数。
    cur_time[26] 用于记录 i~j 之间出现的频率。

    i j  ： 分别是单词前空格，和单词后空格。
    每次跨过空格，， ij重新定位到单词开头。

    用len_p 记录 该单词的长度。
    用count 记录 目前 cur_time 吃了多少字符。
    最终放在 ans 中。
*/

// int main(){
//  // 假设全是小写字母。
//     string str_s; // 输入 句子
//     string str_p; // 被替换的单词
//     string str_r; // 用于替换

//     int time[26] = {0};
    

//     getline(cin,str_s);
//     getline(cin,str_p);
//     getline(cin,str_r);


//     // 标记一个单词
//     int cur_time[26] = {0};
//     int count = 0;

//     int len_s = str_s.length();
//     int len_p = str_p.length();
//     int len_r = str_r.length();

//     for(int i = 0; i < len_p; i++){
//         time[str_p[i] - 'a']++;
//     }
//     // len_s*10 确保 可以放得下 替换后 的字符串
//     char ans[len_s*10];
//     // ans 的下标 k 即为下一个需要填空的 位置，
//     int k = 0;
//     int i = -1;
//     // i指向单词前空格 或则i == -1， j 指向 单词后空格或则 j = str_s 时 为一个完整的单词
//     for(int j = 0; j <= len_s; j++){
//         // 当前 i~j 为一个完整的单词
//         if(j == len_s || str_s[j] == ' '){
//             // 识别单词。
//             bool isit = true;
//             for(int kk = 0; kk < 26; kk++){
//                 // 字符出现频率 于 目标单词 不同。
//                 if(cur_time[kk] != time[kk]){ 
//                     isit = false;
//                 }
//                 // 清空cur_time 为下一个单词做准备
//                 cur_time[kk] = 0;
//             }
//             // 清空 count 为下一个单词做准备
//             count = 0;
//             if(isit){ 
//                 // 保持左侧有空格。
//                 if(i != -1) ans[k++] = ' ';
//                 for(int kk = 0; kk < len_r; kk++){
//                     ans[k++] = str_r[kk];
//                 }
//             }else{
//                 // 扫描到一个单词就填词到ans。 开头一个单词前无空格 (i==-1)
//                 // 除了第一个单词， 填写 str_s[i,j) 左闭右开， 保持 单词左边有空格。
//                 for(int kk = i; kk < j; kk++){
//                     if(kk == -1) continue;
//                     ans[k++] = str_s[kk];
//                 }
//             }
//             // 开始识别下一个单词
//             i = j;
//             // for会给  j++;
//             continue;
//         }
//         // 正常计数
//         cur_time[str_s[j] - 'a']++;
//         count ++;
//     }

//      // 打印语句
//     for(int i = 0; i < k; i++){
//         printf("%c",ans[i]);
//     }


//     //  提交时删除下边
//     printf("\n");
//     char a, b;
//     printf("\n--------------------------\n");
//     scanf("%d", a);
//     return 0;
// }

// int main(){
//  // 假设全是小写字母。
//     string str_s; // 输入 句子
//     string str_p; // 被替换的单词
//     string str_r; // 用于替换

//     int time[26] = {0};
    

//     getline(cin,str_s);
//     getline(cin,str_p);
//     getline(cin,str_r);


//     // 标记一个单词
//     int cur_time[26] = {0};
//     int count = 0;

//     int len_s = str_s.length();
//     int len_p = str_p.length();
//     int len_r = str_r.length();

//     for(int i = 0; i < len_p; i++){
//         time[str_p[i] - 'a']++;
//     }
    
//     char ans[len_s];
//     // ans 的下标 k 即为下一个需要填空的 位置，
//     int k = 0;
//     int i = -1;
//     // i指向单词前空格 或则i == -1， j 指向 单词后空格或则 j = str_s 时 为一个完整的单词
//     for(int j = 0; j <= len_s; j++){

//         // 当前 i~j 为一个完整的单词
//         if(j == len_s || str_s[j] == ' '){
//             // 识别单词。
//             bool isit = true;
//             for(int kk = 0; kk < 26; kk++){
//                 // 字符出现频率 于 目标单词 不同。
//                 if(cur_time[kk] != time[kk]){ 
//                     isit = false;
//                 }
//                 // 清空cur_time 为下一个单词做准备
//                 cur_time[kk] = 0;
//             }
//             // 清空 count 为下一个单词做准备
//             count = 0;

//             if(isit){ 
//                 // 保持左侧有空格。
//                 if(i != -1) ans[k++] = ' ';
//                 for(int kk = 0; kk < len_r; kk++){
//                     ans[k++] = str_r[kk];
//                 }
//                 // 开始识别下一个单词
//                 i = j;
//                 // for会给  j++;
//             }else{
//                 // 扫描到一个单词就填词到ans。 开头一个单词前无空格 (i==-1)
//                 // 除了第一个单词， 填写 str_s[i,j) 左闭右开， 保持 单词左边有空格。
//                 for(int kk = i; kk < j; kk++){
//                     if(kk == -1) continue;
//                     ans[k++] = str_s[kk];
//                 }
//                 // 开始识别下一个单词
//                 i = j;
//                 //// for会给  j++;
//             }
//             continue;
//         }

//         // 提前结束 一个 单词的识别。
//         // j 不是 空格或则越界， 就一定是 字符。
//         // if(count > len_p ||cur_time[str_s[j] - 'a'] == time[str_s[j] - 'a'] || time[str_s[j] - 'a'] == 0){
//         //     // 此时， i 要向后滑动，直到遇到空格，或则结束。
//         //     if(i != -1) ans[k++] = ' '; // 左侧空格。
//         //     i++;     
//         //     while(i < len_s && str_s[i] != ' '){
//         //         ans[k++] = str_s[i++];
//         //     }

//         //     memset(cur_time,0,sizeof(cur_time));
//         //     count = 0;
//         //     j = i;
//         //     // for会给j++;
//         //     continue;
//         // }

//         // 正常计数
//         cur_time[str_s[j] - 'a']++;
//         count ++;
//     }

     
//     for(int i = 0; i < k; i++){
//         printf("%c",ans[i]);
//     }
//     printf("\n");
//     char a, b;
//     printf("\n--------------------------\n");
//     scanf("%d", a);
//     return 0;
// }



// int main(){
//  // 假设全是小写字母。
//     string str_s; // 输入 句子
//     string str_p; // 被替换的单词
//     string str_r; // 用于替换

//     int time[26] = {0};

//     cin >> str_s;
//     cin >> str_p;

//     // 标记一个单词
//     int cur_time[26] = {0};
//     int count = 0;

//     int len_s = str_s.length();
//     int len_p = str_p.length();
//     int len_r = str_r.length();

//     for(int i = 0; i < len_p; i++){
//         time[str_p[i] - 'a']++;
//     }
    
//     char ans[len_s];
//     // ans 的下标 k 即为下一个需要填空的 位置，
//     int k = 0;
//     int i = -1;
//     // i指向单词前空格 或则i == -1， j 指向 单词后空格或则 j = str_s 时 为一个完整的单词
//     for(int j = 0; j <= len_s; j++){

//         // 当前 i~j 为一个完整的单词 ，且长度合格。
//         if(count == len_p && (j == len_s || str_s[j] == ' ')){
//             // 识别单词。
//             bool isit = true;

//             for(int kk = 0; kk < 26; kk++){
//                 // 字符出现频率 于 目标单词 不同。
//                 if(cur_time[kk] != time[kk]){ 
//                     isit = false;
//                 }
//                 // 清空cur_time 为下一个单词做准备
//                 cur_time[kk] = 0;
//             }

//             // 清空 count 为下一个单词做准备
//             count = 0;
//             // 频率相同  即为 需要替换的 单词。
//             if(isit){
//                 // 覆盖需要填写的位置。
//                 k = j == len_s ?  k - (j - i) :  k - (j - i - 1);
//                 // 替换单词 进行填充ans 
//                 for(int kk = 0; kk < len_r; kk++){
//                     ans[k++] = str_r[kk];
//                 }
//             }
//         }

//         // 该单词长度不够，放弃识别。
//         if(str_s[j] == ' '){  // 注意清空 cur_time count
//             i = j;
//             j++;
//             // 清空标记
//             memset(cur_time,0 ,sizeof(cur_time) );
//             count = 0;
//         }


//         // j 不是 空格或则越界， 就一定是 字符。
//         if(cur_time[str_s[j]] == time[str_s[j]]){
//             // 此时， j 要向后滑动，直到遇到空格，或则结束。
//         }


//         // 正常计数
//         cur_time[str_s[j] - 'a']++;
//         count ++;

//         // 正常填充 字符
//         ans[k++] = str_s[j];
//     }

     
//     for(int i = 0; i < k; i++){
//         printf("%c",ans[i]);
//     }
//     printf("\n");
//     char a, b;
//     printf("\n--------------------------\n");
//     scanf("%d", a);
//     return 0;
// }

