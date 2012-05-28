import static ch.qos.logback.classic.Level.*
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.ConsoleAppender;

scan()

appender("CONSOLE", ConsoleAppender){

	encoder(PatternLayoutEncoder) { pattern = "%-4relative [%thread] %-5level %logger{35} - %msg %n" }
}

logger("eu.enhan", DEBUG)
//logger("org.springframework.security", DEBUG)

root(INFO, ["CONSOLE"])
