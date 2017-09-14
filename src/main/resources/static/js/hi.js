$.ajax({
		url : '/test/test',
		type : 'POST',
		async: false,
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		data : JSON.stringify({
			"name" : "민호",// 이름: 한글2, 3글자만가능
			"birthday": "1987­06­22",// 생년월일: 포맷이정확하게일치
			"height": 187,// 키: 160~200 범위의숫자만가능
			"job": "배우",// 직업: 한글, 영문대소문자가능, 10자제한
			"location": "126.9683104,37.344701",// 위치: 경도, 위도좌표
			"hobbys": ["독서", "영화", "축구"],// 취미: 해당키가없거나0개일수있음
			"is_fulfilled": false// 군필여부: true or false
		}
		}),
		success : function(data) {
			console.log("");
		},
		error : function(data) {
			alert("error" + data);
		}
	});