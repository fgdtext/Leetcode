**Q133**     克隆图
             前序递归，进行结点拷贝即可。
             层次遍历，用map：记录新旧结点的映射，来标记一个点是否被复制过。


**Q207**     课程表  ： 拓扑排序
             可以使用常规的拓扑排序，但是说实话，不常写，代码量也偏长。  十分推荐 dfs的拓扑排序。
                public void dfs(int u) {
                    visited[u] = 1;  // 标记结点u正在访问，还没完成全部子节点的访问。
                    for (int v: edges.get(u)) {
                        if (visited[v] == 0) {  // 子节点未访问，则可以访问下去
                            dfs(v);
                            if (!valid) {  // valid是一个全局变量，若上边dfs把valid改为false，说明tuopu失败。
                                return;
                            }
                        } else if (visited[v] == 1) { // ==1,说明遇到了正在访问但是未完成的点，
                            valid = false;            // 这就说明v是父节点，又是子节点，说明遇到了环。
                            return;                     // 修改为false，并返回。
                        }
                    }
                    visited[u] = 2;  // 当所有子节点完成访问，标记访问完了。
                    //如果需要序列，则进行保存。
                   //  ans.addFirst(u) // 使用头插法加入 u. 因为加入结点的顺序恰好和正常顺序逆序。
                    }
                }
                
**Q332**     重新安排行程  ： 一笔画问题  飞机票构成一幅图。然后找到一条路径使用完所有机票。输出每次到达的位置。从JFK出发
             euler问题： 通过dfs，把走过的边标记占用，或者直接删除。当一个结点的所有子结点都访问完了，或者说当一个结点的所有边被标记了，
             那么久输出该点。
                public void dfs(String curr) {
                    while (map.containsKey(curr) && map.get(curr).size() > 0) {
                        String tmp = map.get(curr).poll();
                        dfs(tmp);
                    }
                    itinerary.add(curr);
                }
