byte s;

void setup() {
	pinMode(6, OUTPUT);
	pinMode(7, OUTPUT);
	Serial.begin(9600);
	digitalWrite(6, LOW);
	digitalWrite(7, LOW);
        both();
        off();
	s = 0;
}

void loop() {
	if(Serial.available() > 0) {
		s = Serial.read();
	}
	if(s == 0) {
		off();
	}
	else if (s == 1) {
		left();
	}
	else if (s == 2) {
		right();
	}
	else if (s == 3) {
		both();
	}
}

void left() {
	digitalWrite(6, HIGH);
	digitalWrite(7, LOW);
	delay(50);
	digitalWrite(6, LOW);
	delay(50);
}

void right() {
	digitalWrite(6, LOW);
	digitalWrite(7, HIGH);
	delay(50);
	digitalWrite(7, LOW);
	delay(50);
}

void off() {
	digitalWrite(6, LOW);
	digitalWrite(7, LOW);
	delay(100);
}

void both() {
	digitalWrite(6, LOW);
	digitalWrite(7, HIGH);
	delay(50);
	digitalWrite(6, HIGH);
	digitalWrite(7, LOW);
	delay(50);
}
