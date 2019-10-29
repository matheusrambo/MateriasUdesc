import socket
import sys

opcoes = {'1': 'Cadastrar Novo Usuário',
          '2': 'Mostrar Usuários',
          '3': 'Entrar no Sistema',
          '4': 'Sair'}
users = {"admin": "admin", "rambo": "rambo", "root": "root"}
while True:
    print('Menu: \n')
    print('1: Cadastrar Novo Usuário \n2: Mostrar Usuários \n3: Entrar no Sistema \n4: Sair')
    op = input("Qual opção? ")

    if op == '4':
        print('Saindo do Sistema')
        break
    if op == '2':
        print('Usuários Cadastrados:')
        for i in users:
            print(f'User: {i}')
        print()

    if op == '1':
        login = input("Login: ")
        pswd = input("Password: ")
        users.update({login: pswd})

    if op == '3':
        maxlogin = 3  # maximo de tentativas
        cont = 0

        # Login
        while 1:
            cont = cont + 1
            userid = input("Login: ")
            password = input("Password: ")
            if userid in users:
                if password == users[userid]:
                    print('Login feito com Sucesso!')
                    break
                else:
                    print('Errou a senha meu amigo!')
            else:
                print('Esse Login não existe!')

            if cont == 3:
                print('Digitou 3 vezes Incorretamente, Saindo do sistema!')
                sys.exit(0)


        def Main():
            host = '127.0.0.1'
            port = 5000

            s = socket.socket()
            s.connect((host, port))

            filename = input("Nome do Livro? -> ")
            if filename != 'q':
                s.send(filename.encode('utf-8'))
                data = s.recv(1024)
                data = data.decode('utf-8')
                if data[:6] == 'EXISTS':
                    filesize = str(data[6:])
                    message = input(
                        "Este Livro Possuimos em nossa Biblioteca, " + filesize + " bytes, Deseja fazer o download? (S/N)? -> ")
                    if message == 'S' or 's':
                        s.send(str.encode("OK"))
                        f = open('new_' + filename, 'wb')
                        data = s.recv(1024)
                        totalRecv = len(data)
                        f.write(data)
                        while str(totalRecv) < filesize:
                            data = s.recv(1024)
                            totalRecv += len(data)
                            f.write(data)
                            print("{0:.2f}".format((totalRecv / float(filesize)) * 100) + "% Concluido")
                        print("Download Realizado!")
                        f.close()
                else:
                    print("Desculpe! Não Possuimos esse Livro!")

            s.close()


        if __name__ == '__main__':
            Main()
