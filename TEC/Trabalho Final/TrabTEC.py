def escreverLinha(lista, linha):
    """Recebe uma lista e adiciona os símbolos na lista"""
    lista.append('{} {} {} {} {}\n'.format(linha[0], linha[1], linha[2], linha[3], linha[4]))


def escreverArquivo(nomeArquivo, lista):
    """Recebe o nome do arquivo e escreve todos os itens da lista no arquivo."""
    with open(nomeArquivo, 'w') as arquivo:
        for linha in lista:
            arquivo.write(linha)


print('Insira o nome do arquivo de entrada:')
nomeArquivo = input()

with open(nomeArquivo) as arquivo:
    linhas = arquivo.readlines()

cont = 0
for find in linhas:
    if str(cont) in find:
        cont = cont + 1
        ultimo_estado = cont
ultimo_estado = ultimo_estado - 1
#print(f'O ultimo estado é {ultimo_estado}')

# Converte de ;S para ;I
if linhas[0] == ';S\n':

    listaLinhasConvertidas = [
        ';I\n',
        '0 * * l §1\n',
        '* # # r *\n',
        '§1 * # r §0\n',
    ]

    for linha in linhas:
        # tira o \n e divide entre os espaços
        linha = linha.replace('\n', '').split()

        # Pula linha se não tiver 5 elementos
        if len(linha) != 5:
            continue

        if linha[0] == '0':
            linha[0] = '§0'

        if linha[4] == '0':
            linha[4] = '§0'

        # Para cada linha faz as conversões necessárias e depois salva a linha na lista
        escreverLinha(listaLinhasConvertidas, linha)

    escreverArquivo(nomeArquivo.replace('.txt', '') + 'Convertido.txt', listaLinhasConvertidas)
    print('Agora sua MT é uma MT Duplamente Infinita')


#Converte de ;I para ;S
if linhas[0] == ';I\n':

    listaLinhasConvertidas = [
        ';S\n',
        '0 1 # r ¢1\n',
        '0 0 # r ¢0\n',
        '¢1 1 1 r ¢1\n',
        '¢1 0 1 r ¢0\n',
        '¢1 _ 1 r ¢volta\n',
        '¢0 1 0 r ¢1\n',
        '¢0 0 0 r ¢0\n',
        '¢0 _ 0 l ¢volta\n',
        '¢volta # * r ¢\n',
        '¢volta _ ! l ¢volta\n',
        '¢volta * * l ¢volta\n',
        '¢ _ ! l 1\n',
        '¢ 0 0 r ¢\n',
        '¢ 1 1 r ¢\n',
        '¢ B B r ¢\n',
        '¢ X X r ¢\n',
        '¢ # * r ¢¢branco\n',
        '¢¢branco 1 _ r ¢¢1\n',
        '¢¢branco 0 _ r ¢¢0\n',
        '¢¢branco _ _ r ¢¢_\n',
        '¢¢branco ! _ r ¢¢Final\n',
        '¢¢1 1 1 r ¢¢1\n',
        '¢¢1 0 1 r ¢¢0\n',
        '¢¢1 _ 1 r ¢¢branco\n',
        '¢¢1 ! 1 r ¢¢Final\n',
        '¢¢0 1 0 r ¢¢1\n',
        '¢¢0 0 0 r ¢¢0\n',
        '¢¢0 _ 0 r ¢¢branco\n',
        '¢¢0 ! 0 r ¢¢Final\n',
        '¢¢_ 1 _ r ¢¢1\n',
        '¢¢_ 0 _ r ¢¢0\n',
        '¢¢_ _ _ r ¢¢branco\n',
        '¢¢_ ! _ r ¢¢Final\n',
        '¢¢Final _ ! l ¢¢volta\n',
        '¢¢volta # * r ¢\n',
        '¢¢volta * * l ¢¢volta\n',
        '¢ ! _ r ¢¢brancoFinal\n',
        '¢¢brancoFinal * ! l ¢\n',
    ]
    escreverArquivo(nomeArquivo.replace('.txt', '') + 'Convertido.txt', listaLinhasConvertidas)
    estados = []

    for linha in linhas:
        # tira o \n e divide entre os espaços
        linha = linha.replace('\n', '').split()

        # Pula linha se não tiver 5 elementos
        if len(linha) != 5:
            continue

        if linha[0] == '0':
            continue

        if linha[0] == '0':
            estado = linha[0]

        if not (linha[0] in estados):
            estados.append(linha[0])

        # Para cada linha faz as conversões necessárias e depois salva a linha na lista
        escreverLinha(listaLinhasConvertidas, linha)

    for estado in estados:
        estadoBranco = '¢' + estado + 'branco'
        estado1 = '¢' + estado + '1'
        estado0 = '¢' + estado + '0'
        estado_ = '¢' + estado + '_'
        estadoVolta = '¢' + estado + 'volta'
        estadoBrancoFinal = '¢' + estado + 'brancoFinal'
        estadoFinal = '¢' + estado + 'Final'

        escreverLinha(listaLinhasConvertidas, (estado, '#', '*', 'r', estadoBranco))
        escreverLinha(listaLinhasConvertidas, (estadoBranco, '1', '_', 'r', estado1))
        escreverLinha(listaLinhasConvertidas, (estadoBranco, '0', '_', 'r', estado0))
        escreverLinha(listaLinhasConvertidas, (estadoBranco, '_', '_', 'r', estado_))
        escreverLinha(listaLinhasConvertidas, (estadoBranco, '!', '_', 'r', estadoFinal))
        escreverLinha(listaLinhasConvertidas, (estado1, '1', '1', 'r', estado1))
        escreverLinha(listaLinhasConvertidas, (estado1, '0', '1', 'r', estado0))
        escreverLinha(listaLinhasConvertidas, (estado1, '_', '1', 'r', estadoBranco))
        escreverLinha(listaLinhasConvertidas, (estado1, '!', '1', 'r', estadoFinal))
        escreverLinha(listaLinhasConvertidas, (estado0, '1', '0', 'r', estado1))
        escreverLinha(listaLinhasConvertidas, (estado0, '0', '0', 'r', estado0))
        escreverLinha(listaLinhasConvertidas, (estado0, '_', '0', 'r', estadoBranco))
        escreverLinha(listaLinhasConvertidas, (estado0, '!', '0', 'r', estadoFinal))
        escreverLinha(listaLinhasConvertidas, (estado_, '1', '_', 'r', estado1))
        escreverLinha(listaLinhasConvertidas, (estado_, '0', '_', 'r', estado0))
        escreverLinha(listaLinhasConvertidas, (estado_, '_', '_', 'r', estadoBranco))
        escreverLinha(listaLinhasConvertidas, (estado_, '!', '_', 'r', estadoFinal))
        escreverLinha(listaLinhasConvertidas, (estadoFinal, '_', '!', 'l', estadoVolta))
        escreverLinha(listaLinhasConvertidas, (estadoVolta, '#', '*', 'r', estado))
        escreverLinha(listaLinhasConvertidas, (estadoVolta, '*', '*', 'l', estadoVolta))
        escreverLinha(listaLinhasConvertidas, (estado, '!', '_', 'r', estadoBrancoFinal))
        escreverLinha(listaLinhasConvertidas, (estadoBrancoFinal, '*', '!', 'l', estado))

    escreverArquivo(nomeArquivo.replace('.txt', '') + 'Convertido.txt', listaLinhasConvertidas)
    print('Agora sua MT é uma MT Semi-Infinita')






