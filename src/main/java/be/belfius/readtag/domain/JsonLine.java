package be.belfius.readtag.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Entity
public class JsonLine {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	@Basic
	private java.sql.Timestamp sqlTimestamp;
	 private String jsonId;
	 private String key;
	 private String value;

	public JsonLine(Timestamp sqlTimestamp, String jsonId, String key, String value) {
		this.sqlTimestamp = sqlTimestamp;
		this.jsonId = jsonId;
		this.key = key;
		this.value = value;
	}

	public JsonLine(String jsonId, String key, String value) {
		this.jsonId = jsonId;
		this.key = key;
		this.value = value;
	}
	public JsonLine() {
		super();
	}
}
