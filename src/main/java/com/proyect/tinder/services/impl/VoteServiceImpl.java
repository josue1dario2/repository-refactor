package com.proyect.tinder.services.impl;

import com.proyect.tinder.exception.SpringException;
import com.proyect.tinder.model.Pet;
import com.proyect.tinder.model.Vote;
import com.proyect.tinder.repository.PetRepository;
import com.proyect.tinder.repository.VoteRepository;
import com.proyect.tinder.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

public class VoteServiceImpl implements VoteService {

    private final PetRepository petRepository;
    private final VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(PetRepository petRepository,VoteRepository voteRepository){
        this.petRepository = petRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public void vote(Integer idUser, Integer idPet1, Integer idPet2) throws SpringException {
        Vote vote = new Vote();
        vote.setDate(LocalDateTime.now());

        if(idPet1.equals(idPet2)){
            throw new SpringException("You can't vote for yourself.");
        }

        Optional<Pet> response = petRepository.findById(idPet1);
        if(response.isPresent()){
            Pet pet1 = response.get();
            if(pet1.getUser().getIdUser().equals(idUser)){
                vote.setPet1(pet1);
            }else{
                throw new SpringException("You do not have permissions to perform the requested operation");
            }
        }else{
            throw new SpringException("There is no pet linked to that identifier");
        }

        Optional<Pet> response2 = petRepository.findById(idPet2);
        if(response2.isPresent()){
            Pet pet2 = response2.get();
            vote.setPet2(pet2);
        }else{
            throw new SpringException("There is no pet linked to that identifier");
        }

        voteRepository.save(vote);
    }

    @Override
    public void answerVote(Integer idUser, Integer idVote) throws SpringException {

        Optional<Vote> response = voteRepository.findById(idVote);
        if(response.isPresent()){
            Vote vote = response.get();
            vote.setResponse(LocalDateTime.now());

            if(vote.getPet2().getUser().getIdUser().equals(idUser)){
                voteRepository.save(vote);
            }else{
                throw new SpringException("You do not have permission to perform the operation");
            }
        }else{
            throw new SpringException("The requested vote does not exist");
        }
    }
}
