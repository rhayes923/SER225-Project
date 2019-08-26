MAINCLASSES=$(subst .java,.class,$(wildcard mainGame/*.java))
AUDIO=$(subst .java,.class,$(wildcard mainGame/audio/*.java))
NET=$(subst .java,.class,$(wildcard mainGame/net/*.java))
ENEMY=$(subst .java,.class,$(wildcard mainGame/enemy/*.java))
SPAWN=$(subst .java,.class,$(wildcard mainGame/spawn/*.java))
GUI=$(subst .java,.class,$(wildcard mainGame/gui/*.java))
INPUT=$(subst .java,.class,$(wildcard mainGame/input/*.java))
PICKUP=$(subst .java,.class,$(wildcard mainGame/pickup/*.java))
GFX=$(subst .java,.class,$(wildcard mainGame/gfx/*.java))
MAINDIR=mainGame
NETDIR=$(MAINDIR)/net
AUDIODIR=$(MAINDIR)/audio
IMGDIR=$(MAINDIR)/images
SOUNDDIR=$(MAINDIR)/sounds
SERVERCLASSES=$(subst .java,.class,$(wildcard server/*.java))
JAVABIN=/Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/bin

game: $(MAINCLASSES) $(AUDIO) $(NET) $(ENEMY) $(SPAWN) $(GUI) $(INPUT) $(PICKUP)

server: $(SERVERCLASSES)

%.class: %.java
	$(JAVABIN)/javac $<

all: game server

jar: all
	@echo Class-Path: . >> gamemanifest.mf
	@echo Main-Class: mainGame.Game >> gamemanifest.mf
	@echo Class-Path: . >> servermanifest.mf
	@echo Main-Class: server.Server >> servermanifest.mf
	$(JAVABIN)/jar cvmf gamemanifest.mf gameclient.jar $(MAINDIR)/*.class $(AUDIO) $(NET) $(IMGDIR) $(SOUNDDIR) $(ENEMY) $(SPAWN) $(GUI) $(INPUT) $(GFX) $(PICKUP)
	$(JAVABIN)/jar cvmf servermanifest.mf gameserver.jar $(SERVERCLASSES)
	@rm *.mf

clean:
	rm -f mainGame/*.class
	rm -f mainGame/*/*.class
	rm -f server/*.class
	rm -f *.jar
