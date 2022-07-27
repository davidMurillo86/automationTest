package properties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:configuration.properties",
        "classpath:capabilities.properties"})
public interface PropertiesConfig extends Config {

    @Key("configuration.explicitWait")
    int explicitWait();

    @Key("configuration.env")
    String env();

    @Key("configuration.browser")
    String browser();

    @Key("configuration.testChromeURL")
    String testChromeURL();

    @Key("configuration.testBingURL")
    String testBingURL();

    @Key("configuration.testYoutubeURL")
    String testYoutubeURL();

    @Key("capabilities.runHeadless")
    boolean headless();

    @Key("capabilities.chromeResolution")
    String chromeResolution();

    @Key("capabilities.firefoxResolutionHeight")
    String firefoxResolutionHeight();

    @Key("capabilities.firefoxResolutionWidth")
    String firefoxResolutionWidth();

}
