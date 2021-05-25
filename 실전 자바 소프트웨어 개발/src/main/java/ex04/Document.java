package ex04;

import java.util.Map;

public class Document {
	private final Map<String, String> attributes;
	
	/* 패키지 영역으로 제한된 생성자 */
	Document(final Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}
	
}
