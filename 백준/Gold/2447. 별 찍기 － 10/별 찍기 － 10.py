def make_star(n):
    if n == 1:
        return "*"

    stars = make_star(n // 3)
    next = []
    for star in stars:
        next.append(star * 3)
    for star in stars:
        next.append(star + " " * (n // 3) + star)
    for star in stars:
        next.append(star * 3)

    return next


n = int(input())
print("\n".join(make_star(n)))
