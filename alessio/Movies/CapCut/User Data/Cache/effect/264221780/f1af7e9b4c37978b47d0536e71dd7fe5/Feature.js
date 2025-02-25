const Amaz = effect.Amaz;

class Feature
{
    onInit()
    {
        this.isFirstFrame = true;
        console.error("zgjj onInit");
    }

    onLoadScenes(...scenes)
    {
        console.error("zgjj onLoadScenes");
    }

    onUnloadScenes()
    {
    }
   
    onSetParameter(name, value)
    {
    }
   
    onBeforeAlgorithmUpdate(graphName)
    {
        if (this.isFirstFrame)
        {
            Amaz.AmazingManager.getSingleton("Algorithm").setAlgorithmEnable(graphName, "lens_ii_auto_color", true);
            this.isFirstFrame = false;
        }
        else
        {
            Amaz.AmazingManager.getSingleton("Algorithm").setAlgorithmEnable(graphName, "lens_ii_auto_color", false);
        }


    }

    onAfterAlgorithmUpdate(graphName)
    {
        
    }

    getAlgorithmTexture()
    {
        
    }
   
}

exports.Feature = Feature;