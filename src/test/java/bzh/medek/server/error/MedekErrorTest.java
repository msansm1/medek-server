package bzh.medek.server.error;

import org.junit.Assert;

import org.junit.Test;

import bzh.medek.server.error.MedekError;

public class MedekErrorTest {

	@Test
	public void errorIdTest() {
	    MedekError error = new MedekError(200);
	    Assert.assertEquals(200, error.getErrorId());
	}

    @Test
    public void errorIdArgTest() {
        MedekError error = new MedekError(200, "error message");
        Assert.assertEquals(200, error.getErrorId());
        Assert.assertEquals(new String[] {"error message"}[0], error.getParams()[0]);
    }

    @Test
    public void errorIdArgsTest() {
        MedekError error = new MedekError(200, new String[] {"error message", "big exception"});
        Assert.assertEquals(200, error.getErrorId());
        Assert.assertEquals(new String[] {"error message", "big exception"}[0], error.getParams()[0]);
        Assert.assertEquals(new String[] {"error message", "big exception"}[1], error.getParams()[1]);
    }

    @Test
    public void reasonTest() {
        MedekError error = new MedekError("reason of exception");
        Assert.assertEquals("reason of exception", error.getReason());
    }
}
