		.data
_s0:	.asciiz "salam"
	.space 20
_s1:	.asciiz "salam2"
	.space 20
_s2:	.asciiz "f"
	.align 2
Main:	.space 20
		.text
		.globl main
main:
		li $v0, 4
		la $a0, _s0
		syscall
		la $t1, _s1
		la $t0, Main
		sw $t1, 12($t0)
		la $t1, _s2
		la $t0, Main
		sw $t1, 16($t0)
		li $v0, 4
		la $a0, Main
		addi $a0, $a0 ,12
		lw $a0, 0($a0)
		syscall
		la $t0, Main
		addi $t0, $t0, 12
		lw $t0, 0($t0)
		la $t1, Main
		addi $t1, $t1, 16
		lw $t1, 0($t1)
		move $s0, $t0
_read0:
		lb $t2, 0($t0)
		beq  $t2, 0, _exit0
		addi $t0, $t0, 1
		b _read0
_exit0:
_read1:
		lb $t2, 0($t1)
		beq  $t2, 0, _exit1
		sb $t2, 0($t0)
		addi $t0, $t0, 1
		addi $t1, $t1, 1
		b _read1
_exit1:
		li $v0, 4
		move $a0, $s0
		syscall
		li $t1, 2
		la $t0, Main
		sw $t1, 8($t0)
		jr $ra
