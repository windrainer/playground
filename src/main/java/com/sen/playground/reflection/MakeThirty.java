package com.sen.playground.reflection;

public class MakeThirty {

	public static void main(String[] args) {
		
		int[] elements = {1,3,5,7,9,11,13,15};
		int length = elements.length;
		int total = 0;
		System.out.println("开始计算");
		for(int i=0; i<length; i++) {
			int firstElement = elements[i];
			for(int j=0; j<length; j++) {
				int secondElement = elements[j];
				for(int k=0; k<length; k++) {
					int thirdElement = elements[k];
					total = firstElement + secondElement + thirdElement;
					if(total == 30) {
						System.out.println("找到了组合：" + firstElement + "," + secondElement + "," + thirdElement);
					}
				}			
			}
		}
		System.out.println("没有找到结果");
	}
}
