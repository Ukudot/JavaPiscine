NAME		= Program
COMPILER	= javac
FLAGS		= --release 8
RM		= rm -f
SRC		= Program.java
OBJ 		= $(SRC:.java=.class)
JVM		= java

$(NAME):
	$(COMPILER) $(FLAGS) $(SRC)

all: $(NAME)

clean:
	$(RM) $(OBJ)

fclean: clean

re: fclean all

launch:
	$(JVM) $(NAME)
