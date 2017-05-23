package tools.ia.decition;

public class SigmoideDecition extends AbstractDecition{
public double result(double entry) {
		return 1 / (1 - entry);
	}
}
