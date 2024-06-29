
/*import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.general.DigitalAssetManager;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Assertions.Assert;
import org.junit.jupiter.api.Assertions.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;*/

//@Test
public class DigitalAssetManagerTest {
/*  @Rule
  public final TemporaryFolder tempFolder = new TemporaryFolder();

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Test
  public void countsAssets() throws IOException {
    File icon = tempFolder.newFile("icon.png");
    File assets = tempFolder.newFolder("assets");
    createAssets(assets, 3);

    DigitalAssetManager dam = new DigitalAssetManager(icon, assets);
    assertEquals(3, dam.getAssetCount());
  }

  private void createAssets(File assets, int numberOfAssets) throws IOException {
    for (int index = 0; index < numberOfAssets; index++) {
      File asset = new File(assets, String.format("asset-%d.mpg", index));
      Assert.assertTrue("Asset couldn't be created.", asset.createNewFile());
    }
  }

  @Test
  public void throwsIllegalArgumentExceptionIfIconIsNull() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Icon is null, not a file, or doesn't exist.");
    new DigitalAssetManager(null, null);
  }

  @Test
  public void throwsIndexBoundsException() {
    int [] arr = new int[2];
    exception.expect(AssertionError.class);
    //exception.expectMessage("Icon is null, not a file, or doesn't exist.");
    assertEquals(1,0);
    //System.out.println(arr[4]);
  }*/
}
