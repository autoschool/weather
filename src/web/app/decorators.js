export function className(name) {
    return function(target) {
        target.prototype.className = name;
    };
}