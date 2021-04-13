**Q48**     旋转图像 ： 顺时针旋转
            怎么找映射坐标， 主要是边界距离法。

**Q59**     螺旋矩阵 II  
            while(true){
                for(int i = left; i <= right; i++) ans[up][i] = count++;  // 一直向右走
                if(++up > dowm) break;
                for(int i = up; i <= dowm; i++) ans[i][right] = count++;  // 一直向下走
                if(--right < left) break;
                for(int i = right; i >= left; i--) ans[dowm][i] = count++;  // 一直向左走
                if(--dowm < up) break;
                for(int i = dowm; i >= up; i--) ans[i][left] = count++;   // 一直向上走
                if(++left > right) break;
            }
            **统一模板**


**Q73**       矩阵置零  ： 如果出现一个0，那么该行该列都置为0. 要求原地操作。
              可以借用第一行第一列来表示该行的状态，若置为0，则表示该行或者该列都是0. m[0][0]点要单独判断。



**Q74**       搜索二维矩阵 : 00 到n,m有序。 搜索 target。
              二分搜索最后一列， 搜索第一个大于target的值， 如果没找到，那么就是不存在。
              如果找到第0行，那么若大于第0行第0个元素，那么target就可能在第0行。否则就是小于最小的不存在。
              找到在哪一行，然后对该行进行二分搜索。找不到就是不存在。

**Q240**      搜索二维矩阵 II  横向和纵向都是递增的。
              1 4 7 11 15
              2 5 8 12 19
              3 6 9 16 22
              最好的方法就是就是从左下角开始找。 假设找7   
              比3大，向右找6
              比6大，向右找9
              比9小，向上找8
              比8小，向上找7
              找到。