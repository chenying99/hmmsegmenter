package com.liyuncong.algorithms.hmmsegmenter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 字符/序号键值对集合。用在把发射矩阵中的列号对应上字符。
 * @author yuncong
 *
 */
public class LetterDictionary {
	private Map<String, Integer> dictionary;
	
	private LetterDictionary() {
		int serialNumber = 0;
		dictionary = new HashMap<String, Integer>();
		List<String> sentences = Training.getInstance().getSentences();
		List<String> allWords = new LinkedList<String>();
		for (String sentence : sentences) {
			allWords.addAll(CommonTools.findAllChineseWord
					(sentence));
		}
		for (String word : allWords) {
			int len = word.length();
			for(int i = 0; i < len; i++) {
				String current = word.substring(i, i + 1);
				if (!dictionary.containsKey(current)) {
					dictionary.put(current, serialNumber);
					serialNumber++;
				}
			}
		}
	}
	
	private static class SingletonHolder {
		private static LetterDictionary dictionary = new LetterDictionary();
	}
	
	public static LetterDictionary getInstance() {
		return SingletonHolder.dictionary;
	}
	
	public int size() {
		return dictionary.size();
	}
	
	public Set<String> letters() {
		return dictionary.keySet();
	}
	
	public Integer value(String key) {
		return dictionary.get(key);
	}
}
