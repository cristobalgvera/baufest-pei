class Rectangle {
    constructor(base, height = 0) {
        this._base = base;
        this._height = height;
    }

    getArea() {
        return this._base * this._height;
    }

    get base() {
        return this._base;
    }

    set base(base) {
        return this._base = base;
    }

    static getSomeNumber(a) {
        return a * 4;
    }

}

let figure = new Rectangle(3);
console.log(figure.getArea());
console.log(figure);

console.log(Rectangle.getSomeNumber(3));