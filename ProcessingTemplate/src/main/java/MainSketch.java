import processing.core.PApplet;
import controlP5.*;

// Commented by Camden Hobson. @CamdenHobson on Twitter.
public class MainSketch extends PApplet {
    // this line initializes a PApplet variable, essential if you create more classes.
    public static PApplet mainSketch;

    // this is the section of code that gets run when you run the program.
    public static void main(String [] args) {
        // this line makes the program run the sketch with the settings in "MainSketch" - this class.
        PApplet.main("MainSketch", args);
    }

    // this is run before anything else in the sketch window. Anything here that deals with the program once it's running will crash the code.
    public void settings() {
        size(1000,1080);
        //fullScreen();
    }
    // this code segment is where the variables used in this program are initialized.  The controllers are organized in the same way as the values I link them too, but this is not necessary.
    ControlP5 cp5;
    Slider    slider1, slider2, slider3, slider4,
                sliderH, sliderS, sliderB, sliderA;
    float     valueS1, valueS2, valueS3, valueS4,
                valueSH, valueSS, valueSB, valueSA;
    Bang      bang1,   bang2;
    boolean   valueB1 = true, valueB2 = true;
    Textfield text1;
    String    valueT1;

    int controllerWidth, controllerHeight;
    boolean guiOn = true;
    String [] saveNum;

    // this creates an array that holds every controller used in the program. This isn't automatically completed, it has to be done by hand.
    Controller [] controllers = new Controller[]{slider1,slider2,slider3,slider4,
                                                 sliderH,sliderS,sliderB,sliderA,
                                                 bang1,bang2,
                                                 text1};

    /* the code in this function is called on frame 0 of the program, before draw() is ever called, but after setup().
     * This is where you'll want to start adding values to variables before draw() happens.                            */
    public void setup () {
        colorMode(HSB,360,100,100,100);
        // this line creates a new Controller Interface for "this" PApplet, essential for all controllers on the program.
        cp5 = new ControlP5(this);
        /* variables for a standardized width and height for all controllers.
           floor() knocks off everything past the one's place, removing any decimal values.  This keeps positions on a pixel, so they will always render properly without anti-aliasing. */
        controllerHeight = floor(25 * height / 1000);
        controllerWidth = floor(width / 4 - controllerHeight);
        // this code segment creates sliders from the top left, spaced out by half their height.

        // this line assigns a slider with label "slider1" to the slider1 variable.  The label does not have to be the same as the variable name, but is here for simplicity.
        slider1 = cp5.addSlider("slider1")
                // this sets the minimum value of the slider to 0, and the maximum to 1.
                .setMin(0).setMax(1)
                // this sets the location of the top left corner of the controller
                .setPosition(controllerHeight, controllerHeight)
                // this sets the size of the controller, extending out from the top left corner
                .setSize(controllerWidth, controllerHeight)
                /* this changes the color of the controller's different states
                 * Active is the color of the value segment when the mouse is over the slider, Foreground is the color of the value segment when the mouse isn't over it
                 * Background is self explanatory, and Label is the color of the text denoting the sliders name                                                          */
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,100));
        slider2 = cp5.addSlider("slider2")
                .setMin(0).setMax(1)
                // each slider underneath's position is based on the position above it.
                .setPosition(controllerHeight, floor((float) (slider1.getPosition()[1]+ controllerHeight *1.5)))
                .setSize(controllerWidth, controllerHeight)
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,100));
        slider3 = cp5.addSlider("slider3")
                .setMin(0).setMax(1)
                .setPosition(controllerHeight, floor((float) (slider2.getPosition()[1]+ controllerHeight *1.5)))
                .setSize(controllerWidth, controllerHeight)
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,100));

        // this code segment creates four sliders that act as a color picker.
        sliderH = cp5.addSlider("Hue")
                .setMin(0).setMax(360)
                .setPosition(controllerHeight, slider3.getPosition()[1] + slider3.getHeight()*2)
                // these sliders are 3/4th the size of the others.
                .setSize(floor((controllerWidth)*3/4),floor(controllerHeight *3/4))
                .setColorLabel(color(0,0,100))
                .setValue(360);
        sliderS = cp5.addSlider("Saturation")
                .setMin(0).setMax(100)
                .setPosition(controllerHeight, floor(sliderH.getPosition()[1] + sliderH.getHeight()))
                .setSize(floor((controllerWidth)*3/4),floor(controllerHeight *3/4))
                .setColorLabel(color(0,0,100))
                .setValue(100);
        sliderB = cp5.addSlider("Brightness")
                .setMin(0).setMax(100)
                .setPosition(controllerHeight, floor(sliderS.getPosition()[1] + sliderS.getHeight()))
                .setSize(floor((controllerWidth)*3/4),floor(controllerHeight *3/4))
                .setColorLabel(color(0,0,100))
                .setValue(100);
        sliderA = cp5.addSlider("Alpha")
                .setMin(0).setMax(100)
                .setPosition(controllerHeight, floor(sliderB.getPosition()[1] + sliderB.getHeight()))
                .setSize(floor((controllerWidth)*3/4),floor(controllerHeight *3/4))
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,100))
                .setValue(100);

        // this code segment creates two "bangs", buttons that do something when they are pressed.
        bang1 = cp5.addBang("bang1")
                .setPosition(width - controllerHeight *2, controllerHeight)
                .setSize(controllerHeight, controllerHeight)
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,100));
        bang2 = cp5.addBang("bang2")
                .setPosition(width - controllerHeight *2, bang1.getPosition()[1]+bang1.getHeight()*2)
                .setSize(controllerHeight, controllerHeight)
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,100));
        // this slider is not underneath the other three sliders, and instead sits off to the right underneath the bangs.
        slider4 = cp5.addSlider("slider4")
                .setMin(0).setMax(1)
                .setPosition(width - controllerHeight *2, bang2.getPosition()[1]+bang2.getHeight()*2)
                .setSize(controllerHeight, controllerWidth - controllerHeight *3)
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,100));

        // this code segment creates an interactive textbox in the bottom left of the program.
        text1 = cp5.addTextfield("text1")
                .setPosition(controllerHeight,height- controllerHeight *2)
                .setSize(controllerWidth, controllerHeight)
                .setColorActive(color(0,0,30)).setColorForeground(color(0,0,20)).setColorBackground(color(0,0,10)).setColorLabel(color(0,0,100));

        saveNum = loadStrings("data/saveNum.txt");
        if (saveNum == null) {
            println("Creating new saveNum file...");
            saveStrings("data/saveNum.txt", new String[]{"0"});
        }
        else {
            println("SaveNum = " + saveNum[0]);
        }
    }

    // the code in this function is called every frame by the sketch.  This is essentially the main function of the program, although it may have the least amount of code.
    public void draw () {
        // this line creates a background for the app which essentially "clears" the app each frame, so it starts fresh again.
        background(color(0,0,95));
        // this line calls the syncVars() function.
        syncVars();
        // this segment checks if the guiOn boolean is true, and if it is, displays some bits.
        if (guiOn) {
            // this line makes it so that instead of rect() using the parameters (x1,y1,w1,h1), it takes (x1,y1,x2,y2).
            rectMode(CORNERS);
            // this segment turns off the stroke for any rendered shapes, and then creates a semi-transparent box across the screen.
            noStroke();
            fill(0,0,0,40);
            rect(0,0,width,height);
            // this segment creates a preview for the color selected with the four color picker sliders.
            fill(color(valueSH,valueSS,valueSB));
            rect(sliderH.getPosition()[0]+sliderH.getWidth()+ controllerHeight *2,
                    sliderH.getPosition()[1],
                    floor((float) (sliderH.getPosition()[0]+sliderH.getWidth()*1.1+ controllerHeight *2)),
                    sliderA.getPosition()[1]+sliderA.getHeight());
        }
        // this is a fragment I need to delete
        line(0,height/2,width,height/2);
        line(width/2,0,width/2,height);
    }
    // this function is a placeholder for whatever displaying of the art you would do while the program runs.
    public void display() {

    }
    // this function links all variables' values to the sliders they're represented by.
    public void syncVars () {
        valueS1 = slider1.getValue();
        valueS2 = slider2.getValue();
        valueS3 = slider3.getValue();
        valueS4 = slider4.getValue();

        valueSH = sliderH.getValue();
        valueSS = sliderS.getValue();
        valueSB = sliderB.getValue();
        valueSA = sliderA.getValue();  // these three lines make it so that the color picker sliders dynamically change color depending on their values.
        sliderH.setColorActive(color(valueSH,100,100)).setColorForeground(color(valueSH,100,90)).setColorBackground(color(valueSH,100,80));
        sliderS.setColorActive(color(valueSH,valueSS,100)).setColorForeground(color(valueSH,valueSS,90)).setColorBackground(color(valueSH,valueSS,80));
        sliderB.setColorActive(color(valueSH,100,(float)(valueSB*.8+20))).setColorForeground(color(valueSH,100,(float)(valueSB*.8+10))).setColorBackground(color(valueSH,100,(float)(valueSB*.8)));
    }

    // when the bang1 bang is used, this function will call.
    public void bang1 () {
        println("bang1");
        valueB1 ^= true;
    }
    // when the bang2 bang is used, this function will call.
    public void bang2 () {
        println("bang2");
        valueB2 ^= true;
    }
    // this function is called whenever a key input is registered by the application.
    public void keyPressed() {
        // this line checks to see if the key is a "coded" key- basically anything that isn't a letter or a space.
        if (key == CODED) {
            if (keyCode == UP) {

            }
            if (keyCode == LEFT) {
                guiOn ^= true;
                if (guiOn) {
                    cp5.setVisible(true);
                }
                else {
                    cp5.setVisible(false);
                }
            }
        }
        // this section is for any key that isn't a "code" key.
        else {

        }
    }
}
