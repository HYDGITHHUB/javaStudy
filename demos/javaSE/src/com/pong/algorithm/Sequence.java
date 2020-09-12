package com.pong.algorithm;

/**
 * 首先给出一个长度为2n的序列，我们定义一个值v，
 * 这个值是由如下计算方法得到的，
 * 首先将A序列的第2*i-1位和第2*i位(i=1,2,..,2n-1)进行OR操作得到新数列A'，
 * 然后再对A'序列操作，将A' 序列的第2*i位和第2*i-1位(i=1,2,...,2n-2)进行XOR操作得到A''，
 * 对A''按照第一次操作方式进行OR操作，因为序列长度为2n,
 * 所以显然进行n次操作之后就只剩下一个数字了此时这个数字就是v。
 *
 * 例如A={1，2，3，4}，第一次操作之后为{1|2=3，3|4=7}，第二次操作后，{3^7=4},所以v=4。
 *
 * 但是显然事情并没有那么简单，给出A序列后，还有m个操作，每个操作表示为“a b”，
 * 表示将A[a]改为b，之后再对A序列求v值。
 */
public class Sequence {

}
//
//#include <bits/stdc++.h>
//        using namespace std;
//
//        const int maxN=(1<<18)+2;
//        int tree[4*maxN+50],n,nums[maxN];
//
//        int build(int i,int l,int r){
//        if(l==r){
//        tree[i]=nums[l];
//        return 1;
//        }
//        else{
//        int mid=(l+r)/2,last;
//        last=build(2*i+1,l,mid);
//        last=build(2*i+2,mid+1,r);
//        if(last){
//        tree[i]=tree[2*i+1]|tree[2*i+2];
//        }
//        else{
//        tree[i]=tree[2*i+1]^tree[2*i+2];
//        }
//        return 1-last;
//        }
//        }
//
//        int update(int index,int value,int l,int r,int i){//返回值为1应该使用或，0则为异或
//        if(index<l || index>r){
//        return 0;
//        }
//        if(l==r && l==index){
//        tree[i]=value;
//        return 1;
//        }
//        int mid=(l+r)/2,last;
//        if(index>mid){
//        last=update(index,value,mid+1,r,2*i+2);
//        }
//        else{
//        last=update(index,value,l,mid,2*i+1);
//        }
//        if(last){
//        tree[i]=tree[2*i+1]|tree[2*i+2];
//        }
//        else{
//        tree[i]=tree[2*i+1]^tree[2*i+2];
//        }
//        return 1-last;
//        }
//
//
//        int main(){
//        int m,logn;
//        cin>>logn>>m;
//        n=pow(2,logn);
//        for (int i=0;i<n;i++){
//        scanf("%d",&nums[i]);
//        }
//        build(0,0,n-1);
//        cout<<endl;
//        //dfs(0,0,n-1);
//        cout<<endl;
//        int index,value;
//        for (int i=0;i<m;i++){
//        scanf("%d %d",&index,&value);
//        int j=index-1;
//        update(j,value,0,n-1,0);
//        cout<<tree[0]<<endl;
//        //update(j,nums[j],0,n-1,0);
//        }
//
//        }
