Xi = [-5.0, -4.5, -4.0, -3.5, -3.0, -2.5, -2.0, -1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5, 2.0]
Yi = [3.18, -4.77, 1.91, 2.26, 3.85, 0.02, 1.81, -0.1, -3.22, -2.97, -4.12, 0.25, 3.48, -3.32, 3.03]

grau = len(Yi)

Ai = [ 1 for i in range(grau) ]

for i in range(grau):
    t = 1
    for j in range(grau):
        if i != j:
            t *= (Xi[i]-Xi[j])
    Ai[i] = Yi[i]/t
for i in range(grau):
    print(f'a {i} = {Ai[i]}')
