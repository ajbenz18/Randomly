import tkinter as tk
from tkinter import ttk
import tkinter.font as tf
import requests
from PIL import ImageTk, Image
from io import BytesIO
import clipboard


def autoResizeImage(image):
    # height=image.shape[0]
    # width=image.shape[1]
    print(image.height, image.width)
    larger=image.height
    if (image.width>image.height):
        larger=image.width
    scale=larger/650
    if (image.height>650 or image.width>650):
        image=image.resize((int(image.width//scale), int(image.height/scale)))
        print("resized it")
        print(image.height, image.width)
    return image

class ScrollFrame(tk.Frame): #credit to @mp035 on github. This is his scrollableFrame class
    #https://gist.github.com/mp035/9f2027c3ef9172264532fcd6262f3b01
    def __init__(self, parent):
        super().__init__(parent) # create a frame (self)

        self.canvas = tk.Canvas(self, borderwidth=0, background="light blue")          #place canvas on self
        self.viewPort = tk.Frame(self.canvas, background="light blue")                    #place a frame on the canvas, this frame will hold the child widgets 
        self.vsb = tk.Scrollbar(self, orient="vertical", command=self.canvas.yview) #place a scrollbar on self 
        self.canvas.configure(yscrollcommand=self.vsb.set)                          #attach scrollbar action to scroll of canvas

        self.vsb.pack(side="right", fill="y")                                       #pack scrollbar to right of self
        self.canvas.pack(side="left", fill="both", expand=True)                     #pack canvas to left of self and expand to fil
        self.canvas_window = self.canvas.create_window((4,4), window=self.viewPort, anchor="nw",            #add view port frame to canvas
                                  tags="self.viewPort")
        self.viewPort.bind("<Configure>", self.onFrameConfigure)                       #bind an event whenever the size of the viewPort frame changes.
        self.canvas.bind("<Configure>", self.onCanvasConfigure)                       #bind an event whenever the size of the viewPort frame changes.

        self.onFrameConfigure(None)                                             #perform an initial stretch on render, otherwise the scroll region has a tiny border until the first resize

    def onFrameConfigure(self, event):                                              
        '''Reset the scroll region to encompass the inner frame'''
        self.canvas.configure(scrollregion=self.canvas.bbox("all"))                 #whenever the size of the frame changes, alter the scroll region respectively.

    def onCanvasConfigure(self, event):
        '''Reset the canvas window to encompass inner frame when required'''
        canvas_width = event.width
        self.canvas.itemconfig(self.canvas_window, width = canvas_width)            #whenever the size of the canvas changes alter the window region respectively.

class Menu(tk.Frame):
    def __init__(self, master):
        tk.Frame.__init__(self, master)
        self.master=master
        self.scrollFrame=ScrollFrame(self)
        self.master.title("Randomly Fun")
        self.master.geometry("400x500")
        self.master.geometry("+%d+%d" % (300, 100))
        self.scrollFrame.viewPort.grid_columnconfigure(0, weight=1)
        self.scrollFrame.viewPort.grid_rowconfigure(0, weight=1)
        self.header=tk.Label(self.scrollFrame.viewPort, bg="light blue", text="Randomly Fun", font=headerFont, fg="blue")
        self.header.grid(row=0, column=0, padx=9)
        self.jokeButton=tk.Button(self.scrollFrame.viewPort, height=2, text="Tell me a joke", command=self.populateJoke)
        self.jokeButton.grid(row=1, column=0, sticky="nsew")
        self.factButton=tk.Button(self.scrollFrame.viewPort, height=2, text="Tell me an interesting fact", command=self.populateFact)
        self.factButton.grid(row=2, column=0, sticky="nsew")
        self.adviceButton=tk.Button(self.scrollFrame.viewPort, height=2, text="Give me advice", command=self.populateAdvice)
        self.adviceButton.grid(row=3, column=0, sticky="nsew")
        self.adviceButton=tk.Button(self.scrollFrame.viewPort, height=2, text="Give me a Kanye West Quote", command=self.populateKanyeQuote)
        self.adviceButton.grid(row=4, column=0, sticky="nsew")
        self.adviceButton=tk.Button(self.scrollFrame.viewPort, height=2, text="Give me a famous quote", command=self.populateFamousQuote)
        self.adviceButton.grid(row=5, column=0, sticky="nsew")
        self.adviceButton=tk.Button(self.scrollFrame.viewPort, height=2, text="Give me corporate nonsense", command=self.populateNonsense)
        self.adviceButton.grid(row=6, column=0, sticky="nsew")
        self.adviceButton=tk.Button(self.scrollFrame.viewPort, height=2, text="Give me a Donald Trump quote", command=self.populateTrumpQuote)
        self.adviceButton.grid(row=7, column=0, sticky="nsew")
        self.adviceButton=tk.Button(self.scrollFrame.viewPort, height=2, text="Show me a cute dog picture", command=self.populateDogPic)
        self.adviceButton.grid(row=8, column=0, sticky="nsew")
        self.scrollFrame.pack(side="top", fill="both", expand=True)
        self.displayer=DisplayWindow(self.scrollFrame.viewPort)
        self.displayer.frame.withdraw()
    def findDogBreed(self, url):
        pos=url.rfind("breeds/")+7
        breed=""
        for i in url[pos:]:
            if i=="/":
                break
            else:
                breed+=i
        return breed

    def populateJoke(self):
        try:
            self.displayer.frame.update()
            self.displayer.frame.deiconify()
        except tk.TclError:
            self.displayer=DisplayWindow(self.scrollFrame.viewPort)
        response=requests.get("https://icanhazdadjoke.com", headers={"Accept": "text/plain"})
        self.displayer.item.configure(text=response.text)
        self.displayer.lbl1.configure(text="Joke")
        self.displayer.pic.grid_forget()
    def populateFact(self):
        try:
            self.displayer.frame.update()
            self.displayer.frame.deiconify()
        except tk.TclError:
            self.displayer=DisplayWindow(self.scrollFrame.viewPort)
        response=requests.get("https://uselessfacts.jsph.pl//random.json?language=en")
        self.displayer.item.configure(text=response.json()['text'])
        self.displayer.lbl1.configure(text="Interesting Fact")
        self.displayer.pic.grid_forget()
    def populateAdvice(self):
        try:
            self.displayer.frame.update()
            self.displayer.frame.deiconify()
        except tk.TclError:
            self.displayer=DisplayWindow(self.scrollFrame.viewPort)
        response=requests.get("https://api.adviceslip.com/advice")
        self.displayer.item.configure(text=response.json()['slip']['advice'])
        self.displayer.lbl1.configure(text="Advice")
        self.displayer.pic.grid_forget()
    def populateKanyeQuote(self):
        try:
            self.displayer.frame.update()
            self.displayer.frame.deiconify()
        except tk.TclError:
            self.displayer=DisplayWindow(self.scrollFrame.viewPort)
        response=requests.get("https://api.kanye.rest/")
        self.displayer.item.configure(text='"'+response.json()['quote']+'"')
        self.displayer.lbl1.configure(text="Kanye West Quotes")
        self.displayer.pic.grid_forget()
    def populateFamousQuote(self):
        try:
            self.displayer.frame.update()
            self.displayer.frame.deiconify()
        except tk.TclError:
            self.displayer=DisplayWindow(self.scrollFrame.viewPort)
        response=requests.get("https://api.quotable.io/random")
        print(response.json())
        txt='"'+response.json()['content']+'"'+"\n"+"- "+response.json()['author']
        self.displayer.item.configure(text=txt)
        self.displayer.lbl1.configure(text="Famous Quotes")
        self.displayer.pic.grid_forget()
    def populateNonsense(self):
        try:
            self.displayer.frame.update()
            self.displayer.frame.deiconify()
        except tk.TclError:
            self.displayer=DisplayWindow(self.scrollFrame.viewPort)
        response=requests.get("https://corporatebs-generator.sameerkumar.website/")
        self.displayer.item.configure(text=response.json()["phrase"])
        self.displayer.lbl1.configure(text="Corporate Nonsense")
        self.displayer.pic.grid_forget()
    def populateTrumpQuote(self):
        try:
            self.displayer.frame.update()
            self.displayer.frame.deiconify()
        except tk.TclError:
            self.displayer=DisplayWindow(self.scrollFrame.viewPort)
        response=requests.get("https://api.whatdoestrumpthink.com/api/v1/quotes/random")
        self.displayer.item.configure(text='"'+response.json()['message']+'"')
        self.displayer.lbl1.configure(text="Donald Trump Quote")
        self.displayer.pic.grid_forget()
    def populateDogPic(self):
        global img
        global link
        try:
            self.displayer.frame.update()
            self.displayer.frame.deiconify()
        except tk.TclError:
            self.displayer=DisplayWindow(self.scrollFrame.viewPort)

        response=requests.get("https://dog.ceo/api/breeds/image/random")       
        link=response.json()['message']
        response2 = requests.get(link)
        img_data = response2.content
        img =Image.open(BytesIO(img_data))
        img=autoResizeImage(img)
        img=ImageTk.PhotoImage(img)
        self.displayer.item.configure(text="Breed: "+self.findDogBreed(link))
        self.displayer.pic.configure(image=img)
        self.displayer.pic.grid(row=2, column=0)
        self.displayer.lbl1.configure(text="Cute Dog Picture")


class DisplayWindow:
    def __init__(self, parent):
        self.frame=tk.Toplevel(parent)
        self.frame.maxsize(800, 800)
        self.frame.title("")
        self.frame.configure(bg="light blue")
        self.lbl1=tk.Label(self.frame, text="test", font=headerFont, bg="light blue")
        self.lbl1.config(fg="blue")
        self.lbl1.grid(row=0, column=0)
        self.item=tk.Message(self.frame, text="", justify="center", width=400, bg="light blue")
        self.item.grid(row=1, column=0)
        self.pic=tk.Label(self.frame, image=None)
        self.pic.grid(row=2, column=0)
        self.frame.geometry("+%d+%d" % (1000, 100))
        self.copyButton=tk.Button(self.frame, text="Copy to clipboard", command=self.copyToClipBoard)
        self.copyButton.grid(row=3, column=0)

    def copyToClipBoard(self):
        if (self.lbl1['text']=="Cute Dog Picture"):
             clipboard.copy(link)
        else:
            clipboard.copy(self.item['text'])

img=""
link=""
root=tk.Tk()
fontStyle = tf.Font(family="Lucida Grande", size=20)
headerFont=tf.Font(family="Lucida Grande", size=40)
root.option_add("*Font", fontStyle)
app=Menu(root).pack(side="top", fill="both", expand=True)
root.mainloop()
        

