From LF Require Export Lists.

Definition fold_map {X Y: Type} (f: X -> Y) (l: list X) : list Y :=
  fold (fun x t => f x :: t) l nil.

Example test_fold_map : fold_map (mult 2) [1; 2; 3] = [2; 4; 6].
Proof. reflexivity.  Qed.

Theorem fold_map_correct : forall X Y (f: X -> Y) (l: list X),
  fold_map f l = map f l.
Proof.
  intros.
  induction l.
  reflexivity.
  simpl. rewrite <- IHl. reflexivity.
Qed.

Theorem plus_n_n_injective : forall n m,
     n + n = m + m ->
     n = m.
Proof.
  intros n. induction n as [| n'].
  Case "0". intros m H. destruct m as [|m'].
    reflexivity.
    inversion H.
  Case "S m'". intros m. destruct m as [|m'].
    intros. inversion H.
    intros eq. inversion eq.
    rewrite <- plus_n_Sm in H0. rewrite <- plus_n_Sm in H0.
    inversion H0. apply IHn' in H1. rewrite -> H1. reflexivity.
Qed.

Theorem combine_split' : forall X Y (l : list (X * Y)) l1 l2,
  split l = (l1, l2) ->
  combine l1 l2 = l.
Proof.
  intros X Y l. induction l as [|x xs].
  Case "[]". simpl. intros. inversion H. reflexivity.
  Case "x :: xs". intros l1 l2. destruct x. simpl.
                  intros H. inversion H. simpl.
    assert (Lemma : forall t r, t = r -> (x, y) :: t = (x, y) :: r).
      intros. rewrite H0. reflexivity.
    apply Lemma. apply IHxs. rewrite <- eq_pair. reflexivity.
Qed.

Theorem split_combine' : forall X Y (l1 : list X) (l2 : list Y) (l : list (X*Y)),
  length l1 = length l2 -> combine l1 l2 = l -> split l = (l1, l2).
Proof.
  induction l1 as [|x xs].
  Case "[]". simpl. destruct l2 as [|y ys].
    SCase "[]". simpl. intros. rewrite <- H0. reflexivity.
    SCase "y :: ys". simpl. intros. inversion H.
  Case "x :: xs". destruct l2 as [|y ys].
    SCase "[]". simpl. intros. inversion H.
    SCase "y :: ys". simpl.
                     intros. inversion H. apply IHxs with (l := combine xs ys) in H2.
                     rewrite <- H0. simpl. rewrite -> H2. simpl. reflexivity.
                     reflexivity.
Qed.