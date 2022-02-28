		.data
_s0: .space 20
	.align 2
Main:	.space 20
		.text
		.globl main
main:
		li $v0, 5
		syscall
		move $s0, $v0
		move $t1, $s0
		la $t0, Main
		sw $t1, 12($t0)
		li $v0, 8
		la $a0, _s0
		li $a1, 20
		syscall
		move $s0, $a0
		move $t1, $s0
		la $t0, Main
		sw $t1, 16($t0)
		li $t1, 2
		la $t0, Main
		sw $t1, 8($t0)
		jr $ra
